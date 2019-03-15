package com.movie.example.business.transformer;

import org.springframework.stereotype.Component;

import com.movie.example.core.dto.MovieAndActorsDto;
import com.movie.example.core.dto.MovieDto;
import com.movie.example.core.entity.Movie;

@Component
public class MovieTransformer implements Transformer<Movie, MovieDto> {
	
	public MovieDto toDtoFromEntity(Movie movie) {
		MovieDto movieDto = new MovieDto();
		
		movieDto.setGenre(movie.getGenre());
		movieDto.setTitle(movie.getTitle());
		movieDto.setYear(movie.getYear());
		movieDto.setId(movie.getId());
		
		return movieDto;
	}
	
	public MovieAndActorsDto toMovieAndActorsDtoFromEntity(Movie movie) {
		MovieAndActorsDto movieDto = new MovieAndActorsDto();
		
		movieDto.setGenre(movie.getGenre());
		movieDto.setTitle(movie.getTitle());
		movieDto.setYear(movie.getYear());
		movieDto.setId(movie.getId());
		movieDto.setActors(movie.getMovieActors());
		
		return movieDto;
	}
	
	public Movie toEntityFromDto(MovieDto movieDto) {
		Movie movie = new Movie();
		
		movie.setGenre(movieDto.getGenre());
		movie.setId(movieDto.getId());
		movie.setTitle(movieDto.getTitle());
		movie.setYear(movieDto.getYear());
		
		return movie;
	}
	
	public Movie toEntityFromMovieAndActorsDto(MovieAndActorsDto movieDto) {
		Movie movie = new Movie();
		
		movie.setGenre(movieDto.getGenre());
		movie.setId(movieDto.getId());
		movie.setTitle(movieDto.getTitle());
		movie.setYear(movieDto.getYear());
		movie.setMovieActors(movieDto.getActors());
		
		return movie;
	}
}
