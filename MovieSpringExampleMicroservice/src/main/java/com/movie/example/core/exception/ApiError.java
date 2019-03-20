package com.movie.example.core.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ApiError {
	private HttpStatus status;
	private String message;
	private List<String> errors;
}
