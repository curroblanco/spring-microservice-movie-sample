package com.movie.example.core.service;

import static org.junit.Assert.assertEquals;
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

import com.movie.example.core.dto.MovieAndActorsDto;
import com.movie.example.core.dto.MovieDto;
import com.movie.example.core.entity.Movie;
import com.movie.example.core.repository.MovieJpaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieServiceImplTest {
	
	@Mock
	MovieJpaRepository movieRepository;
	
	@InjectMocks
	MovieServiceImpl movieService = new MovieServiceImpl();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		List<Movie> movieList = new ArrayList<Movie>();
		Movie movie = new Movie(Long.valueOf(1), "Test", "Test", 2019, null);
		movieList.add(movie);
		when(movieRepository.findAll()).thenReturn(movieList);
        when(movieRepository.getOne(Mockito.anyLong())).thenReturn(movie);
	}
	
	@Test
	public void shouldGetAMovieList() {
		List<MovieDto> movies = (List<MovieDto>) movieService.findAll();
		MovieDto currentMovie = movies.get(0);
		
		assertEquals(Long.valueOf(1), currentMovie.getId());
	}

	@Test
	public void shouldGetAMovie() {
		MovieAndActorsDto movieDto = movieService.findOne(Long.valueOf(1));
		assertEquals(Long.valueOf(1), movieDto.getId());
	}

	@Test
	public void shouldReturnMovieId() {
		MovieAndActorsDto movie = new MovieAndActorsDto(Long.valueOf(1), "Test", "Test", 2019, null);
		
		assertEquals(Long.valueOf(1), movieService.insertOne(movie));
	}
	
	@Test
	public void shouldReturnTrueIfDeleted() {
		assertEquals(true, movieService.deleteOne(Long.valueOf(1)));
	}
}
