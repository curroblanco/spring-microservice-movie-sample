package com.movie.example.core.service;

import java.util.Collection;

import com.movie.example.core.dto.MovieAndActorsDto;
import com.movie.example.core.dto.MovieDto;

public interface MovieService {
	
	Collection<MovieDto> findAll();
	MovieAndActorsDto findOne(Long id);
	Long insertOne(MovieAndActorsDto movieDto);
	Boolean deleteOne(Long id);
}
