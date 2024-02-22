package com.dailycode.OrderService.External.Request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
	
	private Long productID;
	private String productName;
	private Long price;
	private Long quantity;
	
}
