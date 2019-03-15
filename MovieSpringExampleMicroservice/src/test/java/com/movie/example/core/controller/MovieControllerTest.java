package com.movie.example.core.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.movie.example.MovieSpringExampleMicroserviceApplication;
import com.movie.example.core.dto.MovieDto;
import com.movie.example.core.entity.Movie;
import com.movie.example.core.repository.MovieJpaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MovieSpringExampleMicroserviceApplication.class, 
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {	
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Autowired
    private MovieJpaRepository movieRepository;
    
	@Test
	@SuppressWarnings("unchecked")
	public void shouldGetAllMoviesAndStatus200() {
		movieRepository.save(new Movie(Long.valueOf(1), "Test", "Test", 1987, null));
	    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<List> response = restTemplate.exchange("http://localhost:" + port + "/movies/",
				HttpMethod.GET, entity, List.class);
		
		List<MovieDto> movieDtoList = response.getBody();
		System.out.print(movieDtoList);
		MovieDto movie = movieDtoList.get(0); 
		
	    assertEquals(Long.valueOf(1), movie.getId());
	    assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void shouldGetOneMovieAndStatus200() throws Exception {
		movieRepository.save(new Movie(Long.valueOf(1), "Test", "Test", 1987, null));
	    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	
		ResponseEntity<MovieDto> response = restTemplate.exchange("http://localhost:" + port + "/movies/1",
				HttpMethod.GET, entity, MovieDto.class);
		
		System.out.print(response.getBody());
		
	    assertEquals(Long.valueOf(1), response.getBody().getId());
	    assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void testInsertMovie() {
		fail("Not yet implemented");
	}

	@Test
	public void shouldGetStatusCode204AndDelete() {
		movieRepository.save(new Movie(Long.valueOf(1), "Test", "Test", 1987, null));
	    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<Object> response = restTemplate.exchange("http://localhost:" + port + "/movies/1",
				HttpMethod.DELETE, entity, Object.class);
		
	    assertEquals(204, response.getStatusCodeValue());
	}

}
