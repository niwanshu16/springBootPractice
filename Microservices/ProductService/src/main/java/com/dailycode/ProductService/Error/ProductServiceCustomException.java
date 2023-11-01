package com.dailycode.ProductService.Error;


public class ProductServiceCustomException extends RuntimeException {
	
	private String errorCode;
	
	public ProductServiceCustomException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
}
