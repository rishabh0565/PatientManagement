package com.pm.billing_service.globalexception;

public class BillNotFoundException extends RuntimeException{
	public BillNotFoundException(String msg) {
		   super(msg); 
	}
}
