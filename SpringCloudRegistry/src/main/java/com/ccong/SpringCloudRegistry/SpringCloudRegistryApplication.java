package com.ccong.SpringCloudRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableEurekaServer
@EnableHystrixDashboard
public class SpringCloudRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudRegistryApplication.class, args);
	}
}
