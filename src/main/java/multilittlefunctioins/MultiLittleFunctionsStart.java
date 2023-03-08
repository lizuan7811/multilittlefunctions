package multilittlefunctioins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import monitorsysinfoutil.multiportdeal.WebPortConfig;

@SpringBootApplication(scanBasePackages = { "multilittlefunctioins", "monitorsysinfoutil" })
public class MultiLittleFunctionsStart {

//	@Autowired
//	private WebPortConfig webPortConfig;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MultiLittleFunctionsStart.class, args);
		WebPortConfig webPortConfig = context.getBean(WebPortConfig.class);
		webPortConfig.servletWebServerFactories().forEach(tctServlet -> {
			tctServlet.getWebServer();
		});
	}

}
