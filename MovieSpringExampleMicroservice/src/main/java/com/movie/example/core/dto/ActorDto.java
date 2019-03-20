package com.movie.example.core.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ActorDto {
	
	private Long id;
	@NotNull private String name;
	@NotNull private String surname;
}
