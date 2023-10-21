package com.dailycode.OrderService.External.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.dailycode.OrderService.External.Request.PaymentRequest;

@FeignClient(name = "PAYMENT-SERVICE", url = "http://localhost:8081/payment")
public interface PaymentService {
	
	@PostMapping
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest request);

}
