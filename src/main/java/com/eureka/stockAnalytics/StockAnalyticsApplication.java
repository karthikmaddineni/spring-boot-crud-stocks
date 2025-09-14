package com.eureka.stockAnalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.eureka.stockAnalytics.remoteService")
public class StockAnalyticsApplication {

	public static void main(String[] args) {

		SpringApplication.run(StockAnalyticsApplication.class, args);
		System.out.println("Successfully runned the maven-project");
	}

}
