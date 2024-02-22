package com.dailycode.OrderService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dailycode.OrderService.External.Error.ErrorResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException exception) {
		return new ResponseEntity<>(new ErrorResponse(exception.getMessage(),exception.getErrorCode()),
				HttpStatus.valueOf(exception.getStatus()));
	}
}
