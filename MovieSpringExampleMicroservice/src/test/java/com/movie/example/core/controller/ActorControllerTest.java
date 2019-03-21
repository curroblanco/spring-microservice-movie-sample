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
import com.movie.example.core.dto.ActorDto;
import com.movie.example.core.entity.Actor;
import com.movie.example.core.repository.ActorJpaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = {MovieSpringExampleMicroserviceApplication.class},
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActorControllerTest {
	
	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();
	private HttpHeaders headers = new HttpHeaders();
	
	@Autowired
    private ActorJpaRepository actorRepository;

	@Test
	public void shouldGetOneActorAndStatus200() {
		actorRepository.save(Actor.builder().id(1L).name("Test").surname("Test").build());
	    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	
		ResponseEntity<ActorDto> response = restTemplate.exchange(String.format("http://localhost:%d/actor/1", port),
				HttpMethod.GET, entity, ActorDto.class);

	    assertEquals(Long.valueOf(1), response.getBody().getId());
	    assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void shouldGetStatusCode204AndDelete() {
		actorRepository.save(Actor.builder().id(1L).name("Test").surname("Test").build());
	    HttpEntity<String> entity = new HttpEntity<String>(null, headers);

	    assertEquals(204, restTemplate.exchange(String.format("http://localhost:%d/actor/1", port),
				HttpMethod.DELETE, entity, Object.class).getStatusCodeValue());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void shouldGetAllActorsAndStatus200() {
		actorRepository.save(Actor.builder().id(1L).name("Test").surname("Test").build());
	    HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<Object> response = restTemplate.exchange(String.format("http://localhost:%d/actor/", port),
				HttpMethod.GET, entity, Object.class);
		
		List<ActorDto> actorDtoList = (List<ActorDto>) response.getBody();

	    assertEquals(1, actorDtoList.size());
	    assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void testInsertActor() {
		ActorDto actorToInsert = ActorDto.builder().id(1L).name("Test").surname("Test").build();

		assertEquals(201, restTemplate
				.postForEntity(String.format("http://localhost:%d/actor/", port), actorToInsert, ActorDto.class)
				.getStatusCodeValue());
	}
	
}
