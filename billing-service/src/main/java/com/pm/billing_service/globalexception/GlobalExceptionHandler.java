package com.pm.billing_service.globalexception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(BillNotFoundException.class)
   public ResponseEntity<Map<String,String>> handleBillNotFoundException(BillNotFoundException ex){
		Map<String,String> map = new HashMap<String, String>(); map.put("Billing ", ex.getLocalizedMessage());
		return ResponseEntity.badRequest().body(map); 
	}
}
