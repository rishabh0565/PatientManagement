package com.pm.billing_service.grpc;

import org.springframework.beans.factory.annotation.Autowired;

import com.pm.billing_service.model.Billing;
import com.pm.billing_service.service.BillingService;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

	@Autowired
	private BillingService billingService;

	@Override
	public void createBillingAccount(BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver) {
		// it will do the business logic some calculation and so on
		Billing billingEntry = billingService.addBillingEntry(billingRequest);
		BillingResponse billingResponse = BillingResponse.newBuilder()
				.setAccountID(billingEntry.getAccountID().toString()).setPatientName(billingEntry.getPatientName())
				.setStatus("Bill Generated Thanks").build();

		responseObserver.onNext(billingResponse);
		responseObserver.onCompleted();
	}
}