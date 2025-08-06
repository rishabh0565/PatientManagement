package com.pm.billing_service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.billing_service.model.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, UUID> {
	public Optional<Billing> findByPatientID(String patientID);

	public boolean existsByPatientID(String patientID);

	public void deleteByPatientID(String patientID);
	
}
