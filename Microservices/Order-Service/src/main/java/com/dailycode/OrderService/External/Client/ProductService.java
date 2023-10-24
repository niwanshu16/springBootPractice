package com.dailycode.OrderService.External.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dailycode.OrderService.Exception.CustomException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@CircuitBreaker(name="External", fallbackMethod="fallback")
@FeignClient(name = "PRODUCT-SERVICE", url="http://localhost:8080/product")
public interface ProductService {
	
	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable("id") Long id, @RequestParam Long quantity);
	
	default void fallback(Exception e) {
		throw new CustomException("Product Service is not available","NOT_AVAILABLE",500);
	}
}
