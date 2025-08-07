package com.pm.patient_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.StreamEndEvent;

import com.pm.patient_service.model.Patient;

import billing.BillingResponse;
import patient.events.DeletePatientEvent;
import patient.events.PatientEvent;

@Service
public class KafkaProducer {
	@Autowired
	private KafkaTemplate<String, byte[]> kafkaTemplate;

	public void sendEvent(Patient patient) {
		PatientEvent event = PatientEvent.newBuilder().setPatientId(patient.getId().toString())
				.setName(patient.getName()).setEmail(patient.getEmail()).setEventType("PATIENT_CREATED").build();
		try {
			kafkaTemplate.send("patient", event.toByteArray()); 
		}
		catch (Exception e) {
			 System.out.println("Error sendint PatientCreated event  "+ event);
		}
	}
	
	public void sendEventToBillingAndAnalyticsWhenSomethingIsDeleted(BillingResponse deleteBillingAccount, Patient patient) {
		DeletePatientEvent event = DeletePatientEvent.newBuilder().setPatientId(patient.getId().toString())
				.setName(patient.getName()).setBillingId(deleteBillingAccount.getAccountID()).build();
		try {
			kafkaTemplate.send("billing", event.toByteArray()); 
		}
		catch (Exception e) {
			 System.out.println("Error sending PatientCreated event  "+ event);
		}
	}
	

}
