package com.movie.example.core.service;

import java.util.Collection;

import com.movie.example.core.dto.ActorDto;

public interface ActorService {
	
	Collection<ActorDto> findAll();
	ActorDto findOne(Long id);
	Long insertOne(ActorDto actor);
	void deleteOne(Long id);
}
