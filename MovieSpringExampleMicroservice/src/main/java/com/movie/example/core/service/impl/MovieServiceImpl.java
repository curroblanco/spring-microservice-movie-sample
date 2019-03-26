package com.movie.example.core.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.movie.example.business.transformer.MovieTransformer;
import com.movie.example.core.dto.MovieDetailDto;
import com.movie.example.core.dto.MovieDto;
import com.movie.example.core.entity.Movie;
import com.movie.example.core.exception.ModelValidator;
import com.movie.example.core.repository.MovieJpaRepository;
import com.movie.example.core.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	private MovieJpaRepository movieRepository;
	private MovieTransformer transformer;
	private ModelValidator validator;
	
	@Autowired
	public MovieServiceImpl(MovieJpaRepository movieRepository, MovieTransformer transformer, ModelValidator validator) {
		this.movieRepository = movieRepository;
		this.transformer = transformer;
		this.validator = validator;
	}

	@Override
	@Cacheable(value = "movieCache")
	public Collection<MovieDto> findAll() {

		Collection<Movie> moviesFromDB = movieRepository.findAll();

		return moviesFromDB.stream()
				.map(transformer::toDtoFromEntity)
					.collect(Collectors.toList());
	}

	@Override
	@Cacheable(value = "singleMovieCache", key = "#id")
	public MovieDetailDto findOne(Long id) {

		return movieRepository.findById(id)
								.map(transformer::toMovieAndActorsDtoFromEntity)
								.orElseThrow();
	}

	@Override
	@CacheEvict(value = "movieCache", allEntries = true)
	public Long insertOne(MovieDetailDto movieDto) {
		
		validator.validate(movieDto);
		Movie movie = transformer.toEntityFromMovieAndActorsDto(movieDto);
		movieRepository.save(movie);
		return movie.getId();
	}

	@Override
	@Caching(evict = {
			@CacheEvict(value = "movieCache", allEntries = true),
			@CacheEvict(value = "singleMovieCache", key = "#id")
	})
	public void deleteOne(Long id) {
		
		movieRepository.deleteById(id);
	}

}
