package com.dailycode.OrderService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dailycode.OrderService.Model.OrderRequest;
import com.dailycode.OrderService.Model.OrderResponse;
import com.dailycode.OrderService.Service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	private OrderService service;
	
	@Autowired
	public OrderController(OrderService service) {
		this.service = service;
	}
	
	@PostMapping("/placeOrder")
	public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
		
		Long orderID = service.placeOrder(orderRequest);
		return new ResponseEntity<>(orderID, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<OrderResponse> getOrder(@PathVariable("id") Long id) {
		OrderResponse orderResponse = service.getOrder(id);
		return new ResponseEntity<>(orderResponse,HttpStatus.OK);
	}
}
