package com.movie.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MovieSpringExampleMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieSpringExampleMicroserviceApplication.class, args);
	}

}
