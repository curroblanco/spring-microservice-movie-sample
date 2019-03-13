package com.movie.example.core.service;

import java.util.Collection;

import com.movie.example.core.dto.MovieDto;

public interface MovieService {
	
	Collection<MovieDto> findAll();
	MovieDto findOne(Long id);
	Long insertOne(MovieDto movieDto);
	void deleteOne(Long id);
}
