package com.movie.example.core.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.movie.example.MovieSpringExampleMicroserviceTestConfiguration;
import com.movie.example.core.dto.MovieAndActorsDto;
import com.movie.example.core.dto.MovieDto;
import com.movie.example.core.entity.Actor;
import com.movie.example.core.entity.Movie;
import com.movie.example.core.repository.MovieJpaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(MovieSpringExampleMicroserviceTestConfiguration.class)
public class MovieServiceImplTest {
	
	@Mock
	MovieJpaRepository movieRepository;
	
	@InjectMocks
	MovieServiceImpl movieService = new MovieServiceImpl();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Set<Actor> actorList = new TreeSet<Actor>();
		List<Movie> movieList = new ArrayList<Movie>();
		Movie movie = new Movie(new Long(1), "Prueba", "Prueba", 2019, actorList);
		movieList.add(movie);
		when(movieRepository.findAll()).thenReturn(movieList);
        when(movieRepository.getOne(Mockito.anyLong())).thenReturn(movie);
	}
	
	@Test
	public void shouldGetAMovieList() {
		
		List<MovieDto> movies = (List<MovieDto>) movieService.findAll();
		System.out.print(movies);
		MovieDto currentMovie = movies.get(0);
		
		assertEquals(new Long(1), currentMovie.getId());
	}

	@Test
	public void shouldGetAMovie() {
		MovieAndActorsDto movieDto = movieService.findOne(new Long(1));
		assertEquals(new Long(1), movieDto.getId());
	}

	@Test
	public void shouldReturnMovieId() {
		MovieAndActorsDto movie = new MovieAndActorsDto(new Long(1), "Prueba", "Prueba", 2019, null);
		
		assertEquals(new Long(1), movieService.insertOne(movie));
	} 
}
