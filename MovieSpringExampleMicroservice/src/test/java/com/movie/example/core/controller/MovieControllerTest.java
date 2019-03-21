package com.movie.example.core.controller;

import static org.junit.Assert.*;

import java.util.List;

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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.movie.example.MovieSpringExampleMicroserviceApplication;
import com.movie.example.core.dto.MovieDetailDto;
import com.movie.example.core.dto.MovieDto;
import com.movie.example.core.entity.Movie;
import com.movie.example.core.repository.MovieJpaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = MovieSpringExampleMicroserviceApplication.class, 
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {	
	
	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();
	private HttpHeaders headers = new HttpHeaders();
	
	@Autowired
    private MovieJpaRepository movieRepository;

	@Test
	public void shouldGetOneMovieAndStatus200() {
		movieRepository.save(Movie.builder().id(1L).title("Test").genre("Test")
								.year(1987).movieActors(null).build());

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<MovieDto> response = restTemplate.exchange(String.format("http://localhost:%d/movie/1", port),
				HttpMethod.GET, entity, MovieDto.class);
		
	    assertEquals(Long.valueOf(1), response.getBody().getId());
	    assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void shouldGetStatusCode204AndDelete() {
		movieRepository.save(Movie.builder().id(1L).title("Test").genre("Test")
								.year(1987).movieActors(null).build());

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

	    assertEquals(204, restTemplate.exchange(String.format("http://localhost:%d/movie/1", port),
				HttpMethod.DELETE, entity, Object.class).getStatusCodeValue());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void shouldGetAllMoviesAndStatus200() {
		movieRepository.save(Movie.builder().id(1L).title("Test").genre("Test")
							.year(1987).movieActors(null).build());
	    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<Object> response = restTemplate.exchange(String.format("http://localhost:%d/movie/", port),
				HttpMethod.GET, entity, Object.class);
		
		List<MovieDto> movieDtoList = (List<MovieDto>) response.getBody();

	    assertEquals(1, movieDtoList.size());
	    assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void testInsertMovie() {
		MovieDetailDto movieToInsert = MovieDetailDto.builder().id(1L).title("Test").genre("Test")
																.year(1987).build();

		assertEquals(201, restTemplate
				.postForEntity(String.format("http://localhost:%d/movie/", port), movieToInsert, MovieDetailDto.class)
				.getStatusCodeValue());
	}

}
