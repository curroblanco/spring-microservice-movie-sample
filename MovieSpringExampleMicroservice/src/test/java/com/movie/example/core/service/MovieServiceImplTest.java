package com.movie.example.core.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.movie.example.core.dto.MovieAndActorsDto;
import com.movie.example.core.dto.MovieDto;
import com.movie.example.core.entity.Movie;
import com.movie.example.core.repository.MovieJpaRepository;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTest {
	
	@Mock
	private MovieJpaRepository movieRepository;
	
	@InjectMocks
	private MovieServiceImpl movieService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Movie movie = new Movie(new Long(1), "Prueba", "Prueba", 2019, "Prueba");
        when(movieRepository.getOne(new Long(1))).thenReturn(movie);
	}
	
	@After
	public void deleteDB() {
		movieRepository.deleteAll();
	}
	
	@Test
	public void shouldGetAMovieList() {
		List<MovieDto> movies = (List<MovieDto>) movieService.findAll();
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
		MovieAndActorsDto movie = new MovieAndActorsDto(new Long(1), "Prueba", "Prueba", 2019, "Prueba");
		
		assertEquals(new Long(1), movieService.insertOne(movie));
	}

}
