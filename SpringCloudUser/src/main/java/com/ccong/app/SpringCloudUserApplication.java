package com.ccong.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages={"com.ccong"})
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.ccong.feign"})
public class SpringCloudUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudUserApplication.class, args);
	}
}
