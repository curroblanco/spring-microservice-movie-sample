package com.movie.example.core.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.movie.example.core.entity.Actor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MovieAndActorsDto {
	
	private Long id;
	@NotNull private String title;
	@NotNull private String genre;
	@NotNull private int year;
	private Set<Actor> actors;
}
