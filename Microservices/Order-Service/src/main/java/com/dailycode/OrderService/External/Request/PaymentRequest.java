package com.dailycode.OrderService.External.Request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
	
	private Long orderID;
	private String referenceNumber;
	private Long amount;
	private String paymentMode;

}
