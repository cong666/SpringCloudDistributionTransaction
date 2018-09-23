package com.ccong.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages={"com.ccong"})
@EnableEurekaClient
public class SpringCloudOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudOrderApplication.class, args);
	}
}
