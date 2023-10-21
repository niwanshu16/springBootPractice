package com.dailycode.OrderService.Model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
	
	private Long orderID;
	private Long productID;
	private Long quantity;
	private Instant OrderDate;
	private String orderStatus;
	private long amount;
	private ProductDetails productDetails;
	private PaymentDetails paymentDetails;
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ProductDetails {
		
		private Long productID;
		private String productName;
		private Long price;
		private Long quantity;
		
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class PaymentDetails {
		
		private Long paymentId;
		private String paymentMode;
		private Long orderId;
		private Instant paymentDate;
		private Long amount;
		private String status;
		
	}

}
