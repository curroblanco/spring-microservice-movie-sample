package com.movie.example.core.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.movie.example.core.entity.Actor;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MovieDetailDto {
	
	private Long id;
	@NotNull private String title;
	@NotNull private String genre;
	@NotNull private int year;
	private Set<Actor> actors;
}
