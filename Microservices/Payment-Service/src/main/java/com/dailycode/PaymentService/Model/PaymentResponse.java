package com.dailycode.PaymentService.Model;

import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
	
	private Long paymentId;
	private PaymentMode paymentMode;
	private Long orderId;
	private Instant paymentDate;
	private Long amount;
	private String status;
	
}
