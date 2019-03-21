package com.movie.example.core.dto;

import javax.validation.constraints.NotNull;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MovieDto {

	private Long id;
	@NotNull private String title;
	@NotNull private String genre;
	@NotNull private int year;
}
