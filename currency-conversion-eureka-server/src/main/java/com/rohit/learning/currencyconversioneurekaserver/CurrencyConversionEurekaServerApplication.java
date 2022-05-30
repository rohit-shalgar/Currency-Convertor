package com.rohit.learning.currencyconversioneurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CurrencyConversionEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionEurekaServerApplication.class, args);
	}

}
