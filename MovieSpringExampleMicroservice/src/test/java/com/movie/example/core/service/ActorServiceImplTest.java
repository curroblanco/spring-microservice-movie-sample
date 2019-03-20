package com.movie.example.core.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.movie.example.core.dto.ActorDto;
import com.movie.example.core.entity.Actor;
import com.movie.example.core.repository.ActorJpaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class ActorServiceImplTest {
	
	@Mock
	ActorJpaRepository actorRepository;
	
	@InjectMocks
	ActorServiceImpl actorService = new ActorServiceImpl();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		List<Actor> actorList = new ArrayList<Actor>();
		Actor actor = new Actor(Long.valueOf(1), "Test", "Test", null);
		actorList.add(actor);
		when(actorRepository.findAll()).thenReturn(actorList);
        when(actorRepository.getOne(Mockito.anyLong())).thenReturn(actor);
	}
	
	@Test
	public void shouldGetAnActorList() {
		List<ActorDto> actors = (List<ActorDto>) actorService.findAll();
		ActorDto currentActor = actors.get(0);
		
		assertEquals(Long.valueOf(1), currentActor.getId());
	}

	@Test
	public void shouldGetAnActor() {
		ActorDto actorDto = actorService.findOne(Long.valueOf(1));
		assertEquals(Long.valueOf(1), actorDto.getId());
	}

	@Test
	public void shouldReturnActorId() {
		ActorDto actorDto = new ActorDto(Long.valueOf(1), "Test", "Test");
		
		assertEquals(Long.valueOf(1), actorService.insertOne(actorDto));
	}
	
	@Test
	public void shouldReturnTrueIfDeleted() {
		assertEquals(true, actorService.deleteOne(Long.valueOf(1)));
	}

}
