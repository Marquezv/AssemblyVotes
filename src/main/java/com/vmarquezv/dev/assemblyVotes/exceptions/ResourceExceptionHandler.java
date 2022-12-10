package com.vmarquezv.dev.assemblyVotes.exceptions;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError>objectNotFound(ObjectNotFoundException err,  HttpServletRequest req ){
		StandardError error = new StandardError(
					Timestamp.valueOf(LocalDateTime.now()),
					HttpStatus.NOT_FOUND.value(),
					err.getMessage(),
					req.getRequestURI()
				
				);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegratyViolationException.class)
	public ResponseEntity<StandardError>dataIntegratyViolation(DataIntegratyViolationException err,  HttpServletRequest req ){
		StandardError error = new StandardError(
					Timestamp.valueOf(LocalDateTime.now()),
					HttpStatus.BAD_REQUEST.value(),
					err.getMessage(),
					req.getRequestURI()
				
				);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<StandardError>invalidFormatException(InvalidFormatException err,  HttpServletRequest req ){
		StandardError error = new StandardError(
					Timestamp.valueOf(LocalDateTime.now()),
					HttpStatus.NOT_ACCEPTABLE.value(),
					err.getMessage(),
					req.getRequestURI()
				);
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
	}
	
}
