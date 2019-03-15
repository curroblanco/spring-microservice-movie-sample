package com.movie.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.movie.example.business.transformer.ActorTransformer;
import com.movie.example.business.transformer.MovieTransformer;

@Configuration
public class MovieSpringExampleMicroserviceTestConfiguration {
	
	@Bean
    public MovieTransformer movieTransformer() {
		MovieTransformer movieTransformer = new MovieTransformer();
        return movieTransformer;
    }
	
	@Bean
    public ActorTransformer actorTransformer() {
		ActorTransformer actorTransformer = new ActorTransformer();
        return actorTransformer;
    }
}
