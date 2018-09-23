package com.ccong.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages={"com.ccong"})
public class SpringCloudDataCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDataCenterApplication.class, args);
	}
}
