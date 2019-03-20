package com.movie.example.core.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.example.business.transformer.ActorTransformer;
import com.movie.example.core.dto.ActorDto;
import com.movie.example.core.entity.Actor;
import com.movie.example.core.exception.ModelValidator;
import com.movie.example.core.repository.ActorJpaRepository;

@Service
public class ActorServiceImpl implements ActorService {
	
	@Autowired
	private ActorJpaRepository actorRepository;
	
	private ModelValidator validator = new ModelValidator();
	
	private ActorTransformer transformer = new ActorTransformer();
	
	@Override
	public Collection<ActorDto> findAll() {
		Collection<Actor> actorFromDB = actorRepository.findAll();
		
		return actorFromDB.stream()
				.map(actor -> transformer.toDtoFromEntity(actor))
					.collect(Collectors.toList());
	}

	@Override
	public ActorDto findOne(Long id) {
		Actor actor = actorRepository.getOne(id);

		return transformer.toDtoFromEntity(actor);
	}

	@Override
	public Long insertOne(ActorDto actorDto) {
		validator.validate(actorDto);
		Actor actor = transformer.toEntityFromDto(actorDto);
		
		actorRepository.save(actor);
		return actor.getId();
	}

	@Override
	public Boolean deleteOne(Long id) {
		
		actorRepository.deleteById(id);
		return true;
	}

}
