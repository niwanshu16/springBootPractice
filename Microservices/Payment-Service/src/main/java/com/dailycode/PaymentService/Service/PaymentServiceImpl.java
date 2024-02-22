package com.dailycode.PaymentService.Service;

import org.springframework.stereotype.Service;

import com.dailycode.PaymentService.Entity.TransactionDetails;
import com.dailycode.PaymentService.Error.PaymentNotFoundException;
import com.dailycode.PaymentService.Model.PaymentMode;
import com.dailycode.PaymentService.Model.PaymentRequest;
import com.dailycode.PaymentService.Model.PaymentResponse;
import com.dailycode.PaymentService.Repository.TransactionDetailsRepository;

import lombok.extern.log4j.Log4j2;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private TransactionDetailsRepository repository;
	

	@Override
	public Long doPayment(PaymentRequest request) {
		// TODO Auto-generated method stub
		log.info("Recording Payment Details {} ", request);
		TransactionDetails transaction = TransactionDetails
									.builder()
									.amount(request.getAmount())
									.orderID(request.getOrderID())
									.paymentMode(PaymentMode.valueOf(request.getPaymentMode()))
									.paymentDate(Instant.now())
									.paymentStatus("SUCCESS")
									.referenceNumber(request.getReferenceNumber())
									.build();
		
		repository.save(transaction);
		log.info("Transaction completed with ID : {} ", transaction.getId());
		return transaction.getId();
	}


	@Override
	public PaymentResponse getPaymentDetailsByOrderId(Long id) {
		
		log.info("INSIDE PAYMENT GET SERVICE");
		TransactionDetails details = repository.findByOrderID(id);
				
		log.info("Got Transaction " + details);
		
		PaymentResponse response = PaymentResponse
					.builder()
					.amount(details.getAmount())
					.orderId(details.getOrderID())
					.paymentDate(details.getPaymentDate())
					.paymentId(details.getId())
					.paymentMode(details.getPaymentMode())
					.status(details.getPaymentStatus())
					.build();
		
		return response;
	}
}
