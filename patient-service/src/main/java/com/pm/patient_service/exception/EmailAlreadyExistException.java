package com.pm.patient_service.exception;

public class EmailAlreadyExistException extends RuntimeException{
	
	public EmailAlreadyExistException(String exeption){
		super(exeption); 
	}

}
