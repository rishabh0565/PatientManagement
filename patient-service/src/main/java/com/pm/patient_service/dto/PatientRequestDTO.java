package com.pm.patient_service.dto;

import org.springframework.stereotype.Component;

import com.pm.patient_service.validationgroup.PatientValidationGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Component
public class PatientRequestDTO {
	@NotBlank(message = "name is required")
	@Size(max = 100, message = "name cannot exceed 100 characters")
	private String name;
	@Email(message = "email should be valid")
	@NotBlank
	private String email;
	@NotNull(message = "address is required")
	private String address;
	@NotNull(message = "Date of birth is required")
	private String dateOfBirth;
	@NotBlank(groups = PatientValidationGroup.class , message = "registration number is required")
	private String registrationDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

}
