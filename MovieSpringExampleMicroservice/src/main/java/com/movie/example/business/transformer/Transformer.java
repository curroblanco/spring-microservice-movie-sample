package com.movie.example.business.transformer;

public interface Transformer <ENTITY, DTO> {
	
	DTO toDtoFromEntity(ENTITY entity);
	ENTITY toEntityFromDto(DTO dto);
}
