package com.dailycode.ProductService.Model;

import lombok.Data;

@Data
public class ProductRequest {
	
	private String productName;
	private Long price;
	private Long quantity;
	
}
