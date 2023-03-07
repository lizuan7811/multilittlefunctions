package multilittlefunctioins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"multilittlefunctioins", "monitorsysinfoutil"})
public class MultiLittleFunctionsStart {
	
	public static void main(String[] args) {
		
		SpringApplication.run(MultiLittleFunctionsStart.class,args);
	}

}
