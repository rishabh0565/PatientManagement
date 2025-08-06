package com.pm.analytics_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.protobuf.InvalidProtocolBufferException;

import patient.events.PatientEvent;

@Service
public class KafkaConsumer {
	@KafkaListener(topics = "patient", groupId = "analytics-service")
	public void consumeEvent(byte[] event) {
		try {
			PatientEvent patientEvent = PatientEvent.parseFrom(event);
			// perform any business related to analytics here .
			System.out.println("Received Patient Event : patientID " + patientEvent.getPatientId() + " name "
					+ patientEvent.getName() + " email " + patientEvent.getEmail());

		} catch (InvalidProtocolBufferException e) {
			System.out.println("Error in deserializing event " + e.getMessage());
		}
	}
}
