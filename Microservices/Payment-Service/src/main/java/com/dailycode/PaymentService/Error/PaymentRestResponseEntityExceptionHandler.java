package com.dailycode.PaymentService.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dailycode.PaymentService.Model.ErrorResponse;

@ControllerAdvice
public class PaymentRestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<ErrorResponse> productNotFoundExceptionHandler(PaymentNotFoundException exception) {
		
		ErrorResponse error = new ErrorResponse(exception.getMessage(),exception.getErrorCode());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		
	}
}
