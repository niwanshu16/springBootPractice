package com.dailycodebuffer.springbootdemo.entity;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
	
	private String message;
	private HttpStatus status;
	
	public ErrorMessage(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
}
