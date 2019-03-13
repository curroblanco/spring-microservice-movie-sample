package com.movie.example.core.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.example.business.transformer.MovieTransformer;
import com.movie.example.core.dto.MovieDto;
import com.movie.example.core.entity.Movie;
import com.movie.example.core.repository.MovieJpaRepository;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieJpaRepository movieRepository;
	
	@Autowired
	private MovieTransformer transformer;
	
	@Override
	public Collection<MovieDto> findAll() {
		Collection<Movie> moviesFromDB = movieRepository.findAll();
		
		return moviesFromDB.stream()
				.map(movie -> transformer.movieToDto(movie))
					.collect(Collectors.toList());
	}

	@Override
	public MovieDto findOne(Long id) {
		
		return transformer.movieToDto(movieRepository.getOne(id));
	}

	@Override
	public Long insertOne(MovieDto movieDto) {
		Movie movie = transformer.movieDtoToEntity(movieDto);
		
		movieRepository.save(movie);
		return movie.getId();
	}

	@Override
	public void deleteOne(Long id) {
		
		movieRepository.deleteById(id);
	}

}
