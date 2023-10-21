package com.dailycode.PaymentService.Error;

import lombok.Data;

@Data
public class PaymentNotFoundException extends RuntimeException {
	
	private String errorCode;
	
	public PaymentNotFoundException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
}
