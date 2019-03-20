package com.movie.example.core.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MovieDto {

	private Long id;
	@NotNull private String title;
	@NotNull private String genre;
	@NotNull private int year;
}
