package com.PetshopDTIBacnkend.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.PetshopDTIBacnkend.dtos.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ContollerExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity threatGeneralExceptions(Exception exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
		return ResponseEntity.internalServerError().body(exceptionDTO);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity threat204(EntityNotFoundException exception) {
		return ResponseEntity.noContent().build();
	}
	
	
}
