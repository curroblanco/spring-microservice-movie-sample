package com.movie.example.core.dto;

import java.util.Set;

import com.movie.example.core.entity.Actor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MovieAndActorsDto {
	
	private Long id;
	private String title;
	private String genre;
	private int year;
	private Set<Actor> actors;
}
