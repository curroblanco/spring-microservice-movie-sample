package com.movie.example.business.transformer;

import org.springframework.stereotype.Component;

import com.movie.example.core.dto.ActorDto;
import com.movie.example.core.entity.Actor;

@Component
public class ActorTransformer implements Transformer<Actor, ActorDto>{

	@Override
	public ActorDto toDtoFromEntity(Actor entity) {
		ActorDto actorDto = new ActorDto();
		actorDto.setId(entity.getId());
		actorDto.setName(entity.getName());
		actorDto.setSurname(entity.getSurname());
		return actorDto;
	}

	@Override
	public Actor toEntityFromDto(ActorDto dto) {
		Actor actor = new Actor();
		actor.setId(dto.getId());
		actor.setName(dto.getName());
		actor.setSurname(dto.getSurname());
		return actor;
	}

}
