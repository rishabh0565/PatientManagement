package com.pm.patient_service.grpc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class BillingServiceGrpcClient {
	private final BillingServiceGrpc.BillingServiceBlockingStub billingServiceBlockingStub;

	public BillingServiceGrpcClient(@Value("${billing.service.address:localhost}") String address,
			@Value("${billing.service.grpc.port:9000}") int serverPort) {

		ManagedChannel channel = ManagedChannelBuilder.forAddress(address, serverPort).usePlaintext().build();
		billingServiceBlockingStub = BillingServiceGrpc.newBlockingStub(channel);

	}

	public BillingResponse createBillingAccount(String patientId, String name, String registrationNo) {
		BillingRequest request = BillingRequest.newBuilder().setPatientID(patientId).setName(name).setRegistrationNo(registrationNo)
				.build();
		BillingResponse response = billingServiceBlockingStub.createBillingAccount(request);
		System.out.println("Receiveed response from billing service vai GRPC " + response);
		return response;
	}
}
