package com.movie.example.business.transformer;

import org.springframework.stereotype.Component;

import com.movie.example.core.dto.MovieDto;
import com.movie.example.core.entity.Movie;

@Component
public class MovieTransformer {
	
	public MovieDto movieToDto(Movie movie) {
		MovieDto movieDto = new MovieDto();
		
		movieDto.setGenre(movie.getGenre());
		movieDto.setTitle(movie.getTitle());
		movieDto.setYear(movie.getYear());
		movieDto.setId(movie.getId());
		
		return movieDto;
	}
	
	public Movie movieDtoToEntity(MovieDto movieDto) {
		Movie movie = new Movie();
		
		movie.setGenre(movieDto.getGenre());
		movie.setId(movieDto.getId());
		movie.setTitle(movieDto.getTitle());
		movie.setYear(movieDto.getYear());
		
		return movie;
	}
}
