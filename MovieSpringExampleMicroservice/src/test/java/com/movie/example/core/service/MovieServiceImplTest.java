package com.movie.example.core.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.movie.example.core.dto.MovieAndActorsDto;
import com.movie.example.core.dto.MovieDto;
import com.movie.example.core.entity.Movie;
import com.movie.example.core.repository.MovieJpaRepository;

@SpringBootTest
public class MovieServiceImplTest {
	
	@Mock
	MovieJpaRepository movieRepository;
	
	@InjectMocks
	MovieServiceImpl movieService = new MovieServiceImpl();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		MovieJpaRepository movieRepository = mock(MovieJpaRepository.class);
		Movie movie = new Movie(new Long(1), "Prueba", "Prueba", 2019, null);
        when(movieRepository.getOne(Mockito.anyLong())).thenReturn(movie);
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
		MovieAndActorsDto movie = new MovieAndActorsDto(new Long(1), "Prueba", "Prueba", 2019, null);
		
		assertEquals(new Long(1), movieService.insertOne(movie));
	} 

}
