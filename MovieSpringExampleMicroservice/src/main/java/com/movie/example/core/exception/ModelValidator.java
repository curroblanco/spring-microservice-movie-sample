package com.movie.example.core.exception;

import javax.validation.Valid;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class ModelValidator {
	
	public void validate(@Valid Object model) {
		
	}
}
