package com.movie.example.core.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javassist.NotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {
	
	private static final String NOT_FOUND_TEXT = "Resource not found";
	
	@ExceptionHandler({EntityNotFoundException.class, NotFoundException.class, NoSuchElementException.class})
	public ResponseEntity<ApiError> handleEntityNotFound(Exception ex) {
		ApiError apiError = new ApiError();
		apiError.setMessage(ex.getMessage());
		apiError.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiError>(apiError, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<ApiError> handleConstraintException(ConstraintViolationException ex) {
		ApiError apiError = new ApiError();
		apiError.setMessage(ex.getLocalizedMessage());
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		List<String> constraintList = new ArrayList<String>();
		for (ConstraintViolation<?> constraintMessage : ex.getConstraintViolations()) {
			constraintList.add(constraintMessage.getPropertyPath() + " : " + constraintMessage.getMessage());
		}
		apiError.setErrors(constraintList);
		return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
	}
}