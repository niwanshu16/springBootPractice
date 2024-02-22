package com.dailycode.PaymentService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycode.PaymentService.Model.PaymentRequest;
import com.dailycode.PaymentService.Model.PaymentResponse;
import com.dailycode.PaymentService.Service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private PaymentService service;
	
	@Autowired
	public PaymentController(PaymentService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest request) {
		Long paymentID = service.doPayment(request);
		return new ResponseEntity<>(paymentID ,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable("id") Long id) {
		PaymentResponse response = service.getPaymentDetailsByOrderId(id);
		return new ResponseEntity<>(response,HttpStatus.FOUND);
	}
}
