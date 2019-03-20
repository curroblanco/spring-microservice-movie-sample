package com.movie.example.core.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.example.business.transformer.MovieTransformer;
import com.movie.example.core.dto.MovieAndActorsDto;
import com.movie.example.core.dto.MovieDto;
import com.movie.example.core.entity.Movie;
import com.movie.example.core.exception.ModelValidator;
import com.movie.example.core.repository.MovieJpaRepository;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieJpaRepository movieRepository;
	
	private ModelValidator validator = new ModelValidator();
	
	private MovieTransformer transformer = new MovieTransformer();
	
	@Override
	public Collection<MovieDto> findAll() {
		Collection<Movie> moviesFromDB = movieRepository.findAll();

		return moviesFromDB.stream()
				.map(movie -> transformer.toDtoFromEntity(movie))
					.collect(Collectors.toList());
	}

	@Override
	public MovieAndActorsDto findOne(Long id) {
		Movie movie = movieRepository.getOne(id);
		
		return transformer.toMovieAndActorsDtoFromEntity(movie);
	}

	@Override
	public Long insertOne(MovieAndActorsDto movieDto) {
		validator.validate(movieDto);
		Movie movie = transformer.toEntityFromMovieAndActorsDto(movieDto);
		
		movieRepository.save(movie);
		return movie.getId();
	}

	@Override
	public Boolean deleteOne(Long id) {
		
		movieRepository.deleteById(id);
		return true;
	}

}
