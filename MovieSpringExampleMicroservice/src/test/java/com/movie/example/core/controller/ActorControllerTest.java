package com.movie.example.core.controller;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

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
import com.movie.example.core.dto.ActorDto;
import com.movie.example.core.entity.Actor;
import com.movie.example.core.repository.ActorJpaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MovieSpringExampleMicroserviceApplication.class, 
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActorControllerTest {
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Autowired
    private ActorJpaRepository actorRepository;

	@Test
	public void shouldGetOneActorAndStatus200() {
		actorRepository.save(new Actor(Long.valueOf(1), "Test", "Test", null));
	    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	
		ResponseEntity<ActorDto> response = restTemplate.exchange("http://localhost:" + port + "/actors/1",
				HttpMethod.GET, entity, ActorDto.class);

	    assertEquals(Long.valueOf(1), response.getBody().getId());
	    assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void shouldGetStatusCode204AndDelete() {
		actorRepository.save(new Actor(Long.valueOf(1), "Test", "Test", null));
	    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<Object> response = restTemplate.exchange("http://localhost:" + port + "/actors/1",
				HttpMethod.DELETE, entity, Object.class);
		
	    assertEquals(204, response.getStatusCodeValue());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetAllActorsAndStatus200() {
		actorRepository.save(new Actor(Long.valueOf(1), "Test", "Test", null));
	    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<Object> response = restTemplate.exchange("http://localhost:" + port + "/actors/",
				HttpMethod.GET, entity, Object.class);
		
		List<ActorDto> actorDtoList = (List<ActorDto>) response.getBody();

		Map<String, Object> currentActor = (Map<String, Object>) actorDtoList.get(0);
		Integer actorId = (Integer) currentActor.get("id");

	    assertEquals(Long.valueOf(1), Long.valueOf(actorId));
	    assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void testInsertActor() {
		ActorDto actorToInsert = new ActorDto(Long.valueOf(1), "Test", "Test");

		ResponseEntity<ActorDto> response = restTemplate
				.postForEntity("http://localhost:" + port + "/actors/", actorToInsert, ActorDto.class);

		assertEquals(201, response.getStatusCodeValue());
	}
	
}
