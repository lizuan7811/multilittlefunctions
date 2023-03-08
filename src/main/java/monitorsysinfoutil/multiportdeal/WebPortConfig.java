package monitorsysinfoutil.multiportdeal;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "serverconfig")
public class WebPortConfig {

	private List<ServerContext> serverContext;

	@PostConstruct
	public List<TomcatServletWebServerFactory> servletWebServerFactories() {
		return serverContext.stream().map(context -> {
			TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
			tomcatServletWebServerFactory.addAdditionalTomcatConnectors(createConnector(context));
			tomcatServletWebServerFactory.setContextPath(context.getContextPath());
			tomcatServletWebServerFactory.setUriEncoding(Charset.defaultCharset());
			tomcatServletWebServerFactory.setPort(context.getPort());
			return tomcatServletWebServerFactory;
		}).collect(Collectors.toList());

	}

	private Connector createConnector(ServerContext context) {
		Connector connector = new Connector(Http11NioProtocol.class.getName());
		connector.setPort(context.getPort());
		connector.setProperty("context-path", context.getContextPath());
//		connector.setURIEncoding("UTF-8");
//		connector.setUseBodyEncodingForURI(true);
//		connector.setProperty("acceptorThreadCount", "1");
//		connector.setProperty("acceptCount", "10");
//		connector.setProperty("keepAliveTimeout", "60000");
//		connector.setProperty("connectionTimeout", "60000");
//		connector.setProperty("maxConnections", "200");
//		connector.setProperty("protocol", "HTTP/1.1");
		return connector;
	}
	
}
