package com.bodyup.ecommerce.control.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bodyup.ecommerce.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

//essa anotação que vai permitir a classe controlar as exception que serão enviadas
@ControllerAdvice
public class ResourceExceptionHandler {

	// tratamento personalizado ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class) //uma anotação responsável por definir um tratamento para exceções específicas
	public ResponseEntity<StandardError> resourceNotFound(
			ResourceNotFoundException e, HttpServletRequest request){
		
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
}
