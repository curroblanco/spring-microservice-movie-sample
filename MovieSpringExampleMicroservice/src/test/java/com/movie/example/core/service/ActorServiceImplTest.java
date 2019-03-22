package com.movie.example.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.*;

import com.movie.example.business.transformer.ActorTransformer;
import com.movie.example.core.dto.MovieDetailDto;
import com.movie.example.core.exception.ModelValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.movie.example.core.dto.ActorDto;
import com.movie.example.core.entity.Actor;
import com.movie.example.core.repository.ActorJpaRepository;
import com.movie.example.core.service.impl.ActorServiceImpl;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@RunWith(SpringJUnit4ClassRunner.class)
public class ActorServiceImplTest {
	
	@Mock
	ActorJpaRepository actorRepository;

	private ActorTransformer actorTransformer = new ActorTransformer();
	private ModelValidator validator = new ModelValidator();

	@InjectMocks
	ActorServiceImpl actorService = new ActorServiceImpl(actorRepository, actorTransformer, validator);

	@Test
	public void shouldGetAnActorList() {
		List<Actor> actorList = new ArrayList<Actor>();
		Actor firstActor = Actor.builder().id(1L).name("Test").surname("Test").build();
		actorList.add(firstActor);
		Actor secondActor = Actor.builder().id(2L).name("Test").surname("Test").build();
		actorList.add(secondActor);
		Actor thirdActor = Actor.builder().id(3L).name("Test").surname("Test").build();
		actorList.add(thirdActor);
		
		when(actorRepository.findAll()).thenReturn(actorList);
		assertEquals(3, actorService.findAll().size());
	}

	@Test
	public void shouldGetAnActor() {
		Actor actor = Actor.builder().id(1L).name("Test").surname("Test").build();

        when(actorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(actor));
		
		ActorDto actorDto = actorService.findOne(1L);
		assertEquals(Long.valueOf(1), actorDto.getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldGetANoSuchElementException() {
		when(actorRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		actorService.findOne(1L);
	}

	@Test
	public void shouldReturnActorId() {
		ActorDto actorDto = ActorDto.builder().id(1L).name("Test").surname("Test").build();
		
		assertEquals(Long.valueOf(1), actorService.insertOne(actorDto));
	}

	@Test
	public void shouldEvaluateNullViolations() {
		Validator validator;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		ActorDto actorDto = ActorDto.builder().id(1L).name(null).surname("Test").build();

		Set<ConstraintViolation<ActorDto>> violations = validator.validate(actorDto);
		assertTrue(!violations.isEmpty());
	}
}
