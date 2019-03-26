package com.movie.example.core.exception;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javassist.NotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

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
		List<String> constraintList = ex.getConstraintViolations().stream().map(
				(constraint) -> String
						.format("%s : %s", constraint.getPropertyPath(), constraint.getMessage())
		).collect(Collectors.toList());
		apiError.setErrors(constraintList);
		return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		ApiError apiError = new ApiError();
		apiError.setMessage(ex.getMostSpecificCause().getMessage());
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
	}
}