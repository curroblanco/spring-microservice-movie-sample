package com.movie.example.core.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.example.business.transformer.ActorTransformer;
import com.movie.example.core.dto.ActorDto;
import com.movie.example.core.entity.Actor;
import com.movie.example.core.repository.ActorJpaRepository;

@Service
public class ActorServiceImpl implements ActorService {
	
	@Autowired
	private ActorJpaRepository actorRepository;
	
	@Autowired
	private ActorTransformer transformer;
	
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
		Actor actor = transformer.toEntityFromDto(actorDto);
		
		actorRepository.save(actor);
		return actor.getId();
	}

	@Override
	public void deleteOne(Long id) {
		
		actorRepository.deleteById(id);
	}

}
