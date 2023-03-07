package multilittlefunctioins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootApplication(scanBasePackages = {"multilittlefunctioins", "monitorsysinfoutil"})
public class MultiLittleFunctionsStart {

	public static void main(String[] args) {
		log.debug("Start MultiLittleFunctionsStart");
		SpringApplication.run(MultiLittleFunctionsStart.class,args);
	}

}

