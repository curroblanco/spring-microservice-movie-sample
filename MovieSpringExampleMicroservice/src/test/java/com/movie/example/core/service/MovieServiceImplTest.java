package com.movie.example.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.*;

import com.movie.example.business.transformer.MovieTransformer;
import com.movie.example.core.exception.ModelValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.movie.example.core.dto.MovieDetailDto;
import com.movie.example.core.entity.Movie;
import com.movie.example.core.repository.MovieJpaRepository;
import com.movie.example.core.service.impl.MovieServiceImpl;

import javax.validation.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieServiceImplTest {
	
	@Mock
	MovieJpaRepository movieRepository;

	private MovieTransformer transformer = new MovieTransformer();
	private ModelValidator validator = new ModelValidator();

	@InjectMocks
	MovieServiceImpl movieService = new MovieServiceImpl(movieRepository, transformer, validator);

	@Test
	public void shouldGetAMovieList() {
		List<Movie> movieList = new ArrayList<>();
		Movie firstMovie = Movie.builder().id(1L).title("Test").genre("Test")
									.year(1987).movieActors(null).build();
		Movie secondMovie = Movie.builder().id(2L).title("Test").genre("Test")
									.year(1987).movieActors(null).build();
		Movie thirdMovie = Movie.builder().id(3L).title("Test").genre("Test")
									.year(1987).movieActors(null).build();

		movieList.add(firstMovie);
		movieList.add(secondMovie);
		movieList.add(thirdMovie);

		when(movieRepository.findAll()).thenReturn(movieList);

		assertEquals(3, movieService.findAll().size());
	}

	@Test
	public void shouldGetAMovie() {
		Movie movie = Movie.builder().id(1L).title("Test").genre("Test")
				.year(1987).movieActors(null).build();

		when(movieRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(movie));

		MovieDetailDto movieDto = movieService.findOne(1L);
		assertEquals(Long.valueOf(1), movieDto.getId());
	}

	@Test
	public void shouldReturnMovieId() {
		MovieDetailDto movie = MovieDetailDto.builder().id(1L).title("Test").genre("Test")
				.year(1987).build();
		
		assertEquals(Long.valueOf(1), movieService.insertOne(movie));
	}

	@Test(expected = NoSuchElementException.class)
	public void shouldGetANoSuchElementException() {
		when(movieRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		movieService.findOne(1L);
	}

	@Test
	public void shouldEvaluateNullViolations() {
		Validator validator;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

		MovieDetailDto movie = MovieDetailDto.builder().id(1L).title(null).genre("Test")
				.year(1987).build();

		Set<ConstraintViolation<MovieDetailDto>> violations = validator.validate(movie);
		assertTrue(!violations.isEmpty());
	}

}
