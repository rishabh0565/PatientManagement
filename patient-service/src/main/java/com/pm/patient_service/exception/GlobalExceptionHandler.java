package com.pm.patient_service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
	
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValdaitonException(MethodArgumentNotValidException ex){
		Map<String,String> map = new HashMap<String, String>(); 
		ex.getBindingResult().getFieldErrors().forEach(er -> map.put(er.getField(), er.getDefaultMessage()));
		return ResponseEntity.badRequest().body(map); 
	}
	
	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<Map<String,String>> handleEmailNotFoundException(EmailAlreadyExistException ex){
		Map<String,String> map = new HashMap<String, String>(); 
		map.put("Message ",  ex.getMessage());
		return ResponseEntity.badRequest().body(map); 
	}
	
	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<Map<String,String>> handleEmailNotFoundException(PatientNotFoundException ex){
		Map<String,String> map = new HashMap<String, String>(); 
		map.put("Message ",  ex.getMessage());
		return ResponseEntity.badRequest().body(map); 
	}
}
