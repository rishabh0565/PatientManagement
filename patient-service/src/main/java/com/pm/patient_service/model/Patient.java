package com.pm.patient_service.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "patient")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private UUID id;
	@NotNull(message = "Name can not be blank")
	@Column(name = "NAME")
	private String name;
	@NotNull
	@Email(message = "email should be valid")
	@Column(name = "EMAIL")
	private String email;
	@NotNull(message = "address is required")
	@Column(name = "ADDRESS")
	private String address;
	@NotNull(message = "date of birth is required")
	@Column(name = "DATE_OF_BIRTH")
	private LocalDate dateOfBirth;
	@NotNull(message = "registration date is required")
	@Column(name = "REGISTERED_DATE")
	private LocalDate registerationDate;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getRegisterationDate() {
		return registerationDate;
	}

	public void setRegisterationDate(LocalDate registerationDate) {
		this.registerationDate = registerationDate;
	}

}
