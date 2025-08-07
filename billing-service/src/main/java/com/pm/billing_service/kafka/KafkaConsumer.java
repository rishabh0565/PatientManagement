package com.pm.billing_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.protobuf.InvalidProtocolBufferException;

import patient.events.DeletePatientEvent;

@Service
public class KafkaConsumer {
	
	@KafkaListener(topics = "billing", groupId = "analytics-delete-consumer")
	public void consumeEventForDelete(byte[] event) {
		try {
			DeletePatientEvent deletePatientEvent = DeletePatientEvent.parseFrom(event);
			// perform any business related to analytics here .
			System.out.println("Received Patient delete evene along with billing : patientID " + deletePatientEvent.getPatientId() + " name "
					+ deletePatientEvent.getName() + " billing Id " + deletePatientEvent.getBillingId());

		} catch (InvalidProtocolBufferException e) {
			System.out.println("Error in deserializing event " + e.getMessage());
		}
	}

}
