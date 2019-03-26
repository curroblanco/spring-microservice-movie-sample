package com.movie.example.core.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.movie.example.core.dto.MovieDetailDto;
import com.movie.example.core.dto.MovieDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api("This com.movie.example.core.controller for accessing Movie EndPoints")
public interface MovieController {
	
	@ApiOperation(value = "Returns a Collection<MovieDto> for all movies", notes = "Returns a list of all movies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returning all Movies successfully"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
	public ResponseEntity<Collection<MovieDto>> getAllMovies();
	
	@ApiOperation(value = "Returns a MovieDetailDto for a given ID", notes = "Returns a movie given an ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returning a Movie successfully"),
            @ApiResponse(code = 404, message = "Movie not found"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
	public ResponseEntity<MovieDetailDto> getOneMovie(@PathVariable Long id);
	
	@ApiOperation(value = "Creates a Movie given a Movie and an ActorList", notes = "Returns new movie Resource access through"
			+ " Location header")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Movie created successfully"),
            @ApiResponse(code = 400, message = "Bad request while creating the movie"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
	public ResponseEntity<Void> insertMovie(@RequestBody MovieDetailDto movieDto);
	
	@ApiOperation(value = "Deletes a movie given an ID", notes = "Returns empty body and 204 status")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Movie deleted successfully"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
	public ResponseEntity<Void> deleteOneMovie(@PathVariable Long id);
}
