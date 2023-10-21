package com.dailycode.PaymentService.Model;

import lombok.Data;

@Data
public class PaymentRequest {
	
	private Long orderID;
	private String referenceNumber;
	private Long amount;
	private String paymentMode;

}
