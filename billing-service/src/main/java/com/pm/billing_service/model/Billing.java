package com.pm.billing_service.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "billing")
public class Billing {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "accountID")
	private UUID accountID;

	@Column(name = "patientName")
	private String patientName;
	
	@Column(name = "patientID")
	private String patientID;
	
	@Column(name = "RegisterationDate")
	private String registrationDate;

	public UUID getAccountID() {
		return accountID;
	}

	public void setAccountID(UUID accountID) {
		this.accountID = accountID;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	} 	

}
