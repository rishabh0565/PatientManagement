package com.pm.billing_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pm.billing_service.globalexception.BillNotFoundException;
import com.pm.billing_service.model.Billing;
import com.pm.billing_service.repository.BillingRepository;

import billing.BillingRequest;
import billing.BillingRequestDelete;

@Service
public class BillingService {

	@Autowired
	private BillingRepository billingRepository;

	public List<Billing> getAllBillingDetails() {
		return billingRepository.findAll();
	}

	public Billing addBillingEntry(BillingRequest billingRequest) {
		Billing bill = new Billing();
		bill.setPatientID(billingRequest.getPatientID());
		bill.setPatientName(billingRequest.getName());
		bill.setRegistrationDate(billingRequest.getRegistrationNo());
		Billing save = billingRepository.save(bill);
		return save;

	}
    @Transactional
	public String deleteBillingEntry(BillingRequestDelete billingRequestDelete) {
		if (!billingRepository.existsByPatientID(billingRequestDelete.getPatientID())) {
			throw new BillNotFoundException(
					"Fot this patient ID -> \"" + billingRequestDelete.getPatientID() + "\" bill is not generated");
		}
		billingRepository.deleteByPatientID(billingRequestDelete.getPatientID());
		return billingRequestDelete.getPatientID() + " Patient ID was deleted sucessfully";
	}

	public Billing findBillDetailsForPatientId(String patientID) {
		return billingRepository.findByPatientID(patientID).orElseThrow(
				() -> new BillNotFoundException("Fot this patient ID -> \"" + patientID + "\" bill is not generated"));
	}

	public Billing findBillByBillingId(UUID billingId) {
		Billing bill = billingRepository.findById(billingId).orElseThrow(() -> new BillNotFoundException(
				"Fot this billing ID -> -> \"" + billingId.toString() + "\" bill is not generated"));
		return bill;
	}

	public String deleteAllBilling() {
		billingRepository.deleteAll();
		return "Deleted All Bills Sucessfully";
	}
}
