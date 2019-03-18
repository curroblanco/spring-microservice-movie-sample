package com.movie.example.core.controller;

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

import com.movie.example.core.dto.ActorDto;
import com.movie.example.core.service.ActorService;

@RestController
@RequestMapping("/actors")
public class ActorController {

	@Autowired
	private ActorService actorService;
	
	@GetMapping
	public ResponseEntity<Collection<ActorDto>> getAllActors() {
		
		return new ResponseEntity<Collection<ActorDto>>(actorService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ActorDto> getOneActor(@PathVariable Long id) {
		
		return new ResponseEntity<ActorDto>(actorService.findOne(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> insertActor(@RequestBody ActorDto actorDto) {
		Long actorId = actorService.insertOne(actorDto);
		HttpHeaders headers = new HttpHeaders();
		
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(actorId).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
		actorService.deleteOne(id);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
