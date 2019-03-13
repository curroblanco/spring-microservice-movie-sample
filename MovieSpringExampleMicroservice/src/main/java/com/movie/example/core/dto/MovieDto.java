package com.movie.example.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class MovieDto {

	private Long id;
	private String title;
	private String genre;
	private int year;
}
