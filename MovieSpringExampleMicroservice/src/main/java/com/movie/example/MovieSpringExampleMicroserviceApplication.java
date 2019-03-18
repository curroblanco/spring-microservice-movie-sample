package com.movie.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieSpringExampleMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieSpringExampleMicroserviceApplication.class, args);
	}

}
