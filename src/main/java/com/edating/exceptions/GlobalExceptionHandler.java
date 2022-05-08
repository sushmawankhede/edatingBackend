package com.edating.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.edating.model.exception.ExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ExceptionResponse> handler(InvalidDataException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value());
		return  new ResponseEntity<>(exception,
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(UserNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(exception,
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InterestNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(InterestNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(exception,
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserNotMatchException.class)
	public ResponseEntity<ExceptionResponse> handler(UserNotMatchException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value());
		return  new ResponseEntity<>(exception,
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoMatchFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(NoMatchFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value());
		return  new ResponseEntity<>(exception,
				HttpStatus.BAD_REQUEST);
	}

}
