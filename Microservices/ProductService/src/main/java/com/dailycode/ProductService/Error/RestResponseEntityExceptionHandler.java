package com.dailycode.ProductService.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dailycode.ProductService.Model.ErrorResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ProductServiceCustomException.class)
	public ResponseEntity<ErrorResponse> productNotFoundExceptionHandler(ProductServiceCustomException exception) {
				
		return new ResponseEntity<>(
				ErrorResponse.builder()
				.message(exception.getMessage())
				.errorCode(exception.getErrorCode())
				.build(), HttpStatus.NOT_FOUND);
	}
	
}
