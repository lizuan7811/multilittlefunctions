package little_func_swagifs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.EndpointLinksResolver;
import org.springframework.boot.actuate.endpoint.web.EndpointMapping;
import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket baseApiInfo() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).paths(Predicate.not(PathSelectors.regex("/error/*"))).build()
				.apiInfo(apiInfo());
	}
	@Bean
	public ApiInfo apiInfo() {
		Contact contact = new Contact("lizuan", "/lizswagger", "mayahate77@gmail.com");
		return new ApiInfo("Test Swagger", "Lizuan Test Swagger", "v1.0", "", contact, "", "",
				new ArrayList<VendorExtension>());
	}

//	配置文件
	@Bean
	public Docket createRestApi(ApiInfo apiInfo) {
		return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo).groupName("SwaggerGroupOneAPI").select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build();
	}

//	可解決Spring Boot 6.x與Swagger 3.0.0不兼容問題
	@Bean
	public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier,
			ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointSupplier,
			EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsEndpointProperties,
			WebEndpointProperties webEndpointProperties, Environment environment) {
		{
			List<ExposableEndpoint> allEndpoints = new ArrayList<ExposableEndpoint>();
			// 取得
			Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
			allEndpoints.addAll(webEndpoints);
			allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
			allEndpoints.addAll(controllerEndpointSupplier.getEndpoints());
			String basePath = webEndpointProperties.getBasePath();

			EndpointMapping endpointMapping =new EndpointMapping(basePath);
			boolean shouldRegisterLinksMapping=this.shouldRegisterLinksMapping(webEndpointProperties, environment,basePath);
			
			return new WebMvcEndpointHandlerMapping(endpointMapping,webEndpoints,endpointMediaTypes,corsEndpointProperties.toCorsConfiguration(),new EndpointLinksResolver((Collection<? extends ExposableEndpoint<?>>) allEndpoints,basePath),shouldRegisterLinksMapping,null);
		}
	}

	private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties,Environment environment,String basePath) {
		return webEndpointProperties.getDiscovery().isEnabled()&& (StringUtils.hasText(basePath)||ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
	}
}
