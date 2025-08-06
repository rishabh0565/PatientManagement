package com.pm.billing_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.billing_service.model.Billing;
import com.pm.billing_service.service.BillingService;

@RestController
@RequestMapping("/billing")
public class BillingController {
   
	@Autowired
	private BillingService billingService; 
	
	@GetMapping("/all")
	public ResponseEntity< List<Billing>> getAllBills(){
		 List<Billing> allBillingDetails = billingService.getAllBillingDetails();
		 return ResponseEntity.ok().body(allBillingDetails); 
	}
	
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<Billing> getBillForPatientID(@PathVariable String patientId){
		Billing bill = billingService.findBillDetailsForPatientId(patientId);
		 return ResponseEntity.ok().body(bill);  
	}
	
	@GetMapping("/bill/{billingID}")
	public ResponseEntity<Billing> getBillForPatientID(@PathVariable UUID billingID){
		Billing bill = billingService.findBillByBillingId(billingID);
		 return ResponseEntity.ok().body(bill);  
	}
	
	@DeleteMapping("/patient/{deletePatientID}")
	public ResponseEntity<Billing> deletePatientID(@PathVariable UUID deletePatientID){
		Billing bill = billingService.findBillByBillingId(deletePatientID);
		 return ResponseEntity.ok().body(bill);  
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAllBilling(){
		String deleteAllBilling = billingService.deleteAllBilling(); 
		return ResponseEntity.ok().body(deleteAllBilling); 
	}
}
