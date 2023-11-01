package com.dailycode.OrderService.Model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import com.dailycode.OrderService.External.Request.PaymentMode;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
	
	private Long amount;
	private Long productID;
	private Long quantity;
	private PaymentMode paymentMode;
	
}
