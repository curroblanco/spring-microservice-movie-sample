package com.movie.example.core.dto;

import javax.validation.constraints.NotNull;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ActorDto {
	
	private Long id;
	@NotNull private String name;
	@NotNull private String surname;
}
