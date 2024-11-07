package com.example.laboservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.laboservice")
public class LaboServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboServiceApplication.class, args);
	}

}
