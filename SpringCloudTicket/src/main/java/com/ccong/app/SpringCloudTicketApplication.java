package com.ccong.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages={"com.ccong"})
@EnableEurekaClient
public class SpringCloudTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudTicketApplication.class, args);
	}
}
