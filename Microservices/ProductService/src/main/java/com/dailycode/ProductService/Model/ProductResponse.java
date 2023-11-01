package com.dailycode.ProductService.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
	
	private Long productID;
	private String productName;
	private Long price;
	private Long quantity;
	
}
