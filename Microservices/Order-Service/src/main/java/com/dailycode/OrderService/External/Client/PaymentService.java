package com.dailycode.OrderService.External.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dailycode.OrderService.Exception.CustomException;
import com.dailycode.OrderService.External.Request.PaymentRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@CircuitBreaker(name="External", fallbackMethod="fallback")
@FeignClient(name = "PAYMENT-SERVICE", url = "http://localhost:8081/payment")
public interface PaymentService {
	
	@PostMapping
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest request);
	
	default void fallback(Exception e) {
		throw new CustomException("Payment Service is not available","NOT_AVAILABLE",509);
	}
}
