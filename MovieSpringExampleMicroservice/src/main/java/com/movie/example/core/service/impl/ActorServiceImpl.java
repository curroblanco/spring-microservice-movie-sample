package com.movie.example.core.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.example.business.transformer.ActorTransformer;
import com.movie.example.core.dto.ActorDto;
import com.movie.example.core.entity.Actor;
import com.movie.example.core.exception.ModelValidator;
import com.movie.example.core.repository.ActorJpaRepository;
import com.movie.example.core.service.ActorService;

@Service
public class ActorServiceImpl implements ActorService {
	
	private ActorJpaRepository actorRepository;
	private ModelValidator validator;
	private ActorTransformer transformer;

	@Autowired
	public ActorServiceImpl(ActorJpaRepository actorRepository, ActorTransformer transformer,
							ModelValidator validator) {
		this.actorRepository = actorRepository;
		this.transformer = transformer;
		this.validator = validator;
	}
	
	@Override
	public Collection<ActorDto> findAll() {
		Collection<Actor> actorFromDB = actorRepository.findAll();
		
		return actorFromDB.stream()
				.map(transformer::toDtoFromEntity)
					.collect(Collectors.toList());
	}

	@Override
	public ActorDto findOne(Long id) {
		return actorRepository.findById(id)
							.map(transformer::toDtoFromEntity)
							.orElseThrow();
	}

	@Override
	public Long insertOne(ActorDto actorDto) {
		validator.validate(actorDto);
		Actor actor = transformer.toEntityFromDto(actorDto);
		
		actorRepository.save(actor);
		return actor.getId();
	}

	@Override
	public void deleteOne(Long id) {
		
		actorRepository.deleteById(id);
	}

}
