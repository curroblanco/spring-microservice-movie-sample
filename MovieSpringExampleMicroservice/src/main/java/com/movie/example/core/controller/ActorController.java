package com.movie.example.core.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.movie.example.core.dto.ActorDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("This com.movie.example.core.controller for accessing Actor EndPoints")
public interface ActorController {
	
	@ApiOperation(value = "Returns a Collection<ActorDto> for all actors", notes = "Returns a list of all actors")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returning all Actors successfully"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
	public ResponseEntity<Collection<ActorDto>> getAllActors();
	
	@ApiOperation(value = "Returns a ActorDto for a given ID", notes = "Returns an actor given an ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returning an Actor successfully"),
            @ApiResponse(code = 404, message = "Movie not found"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
	public ResponseEntity<ActorDto> getOneActor(@PathVariable Long id);
	
	@ApiOperation(value = "Creates an Actor given an ActorDto", notes = "Returns new Actor Resource access through"
			+ " Location header")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Actor created successfully"),
            @ApiResponse(code = 400, message = "Bad request while creating the actor"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
	public ResponseEntity<Void> insertActor(@RequestBody ActorDto actorDto);
	
	@ApiOperation(value = "Deletes an actor given an ID", notes = "Returns empty body and 204 status")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Actor deleted successfully"),
            @ApiResponse(code = 500, message = "Internal server error")}
    )
	public ResponseEntity<Void> deleteActor(@PathVariable Long id);
}
