package com.movie.example.core.controller.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.movie.example.core.controller.MovieController;
import com.movie.example.core.dto.MovieDetailDto;
import com.movie.example.core.dto.MovieDto;
import com.movie.example.core.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieControllerImpl implements MovieController {

	private MovieService movieService;
	
	@Autowired
	public MovieControllerImpl(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping
	public ResponseEntity<Collection<MovieDto>> getAllMovies() {
		
		return new ResponseEntity<Collection<MovieDto>>(movieService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieDetailDto> getOneMovie(@PathVariable Long id) {
		
		return new ResponseEntity<MovieDetailDto>(movieService.findOne(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> insertMovie(@RequestBody MovieDetailDto movieDto) {
		Long movieId = movieService.insertOne(movieDto);
		HttpHeaders headers = new HttpHeaders();
		
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(movieId).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOneMovie(@PathVariable Long id) {
		movieService.deleteOne(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
