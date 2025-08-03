package com.pm.patient_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pm.patient_service.model.Patient;

@org.springframework.stereotype.Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
	public boolean existsByEmail(String email);
	
	public Patient findByEmail(String email); 

}
