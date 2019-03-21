package com.movie.example.core.service;

import java.util.Collection;

import com.movie.example.core.dto.MovieDetailDto;
import com.movie.example.core.dto.MovieDto;

public interface MovieService {
	
	Collection<MovieDto> findAll();
	MovieDetailDto findOne(Long id);
	Long insertOne(MovieDetailDto movieDto);
	void deleteOne(Long id);
}
