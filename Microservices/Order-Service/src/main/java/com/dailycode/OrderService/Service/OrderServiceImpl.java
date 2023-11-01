package com.dailycode.OrderService.Service;

import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dailycode.OrderService.Entity.Order;
import com.dailycode.OrderService.Exception.CustomException;
import com.dailycode.OrderService.External.Client.PaymentService;
import com.dailycode.OrderService.External.Client.ProductService;
import com.dailycode.OrderService.External.Request.PaymentRequest;
import com.dailycode.OrderService.External.Request.PaymentResponse;
import com.dailycode.OrderService.External.Request.ProductResponse;
import com.dailycode.OrderService.Model.OrderRequest;
import com.dailycode.OrderService.Model.OrderResponse;
import com.dailycode.OrderService.Repository.OrderRepository;


import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	private OrderRepository repository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	public OrderServiceImpl(OrderRepository repository) {
		this.repository = repository;
	}

	@Override
	public Long placeOrder(OrderRequest orderRequest) {
		log.info("Placing Order Request {} ", orderRequest);
		try {
			
			productService.reduceQuantity(orderRequest.getProductID(),orderRequest.getQuantity());
		
		
			log.info("Reduced the quantity of the product with ID " + orderRequest.getProductID());
			Order order = Order
							.builder()
							.amount(orderRequest.getAmount())
							.quantity(orderRequest.getQuantity())
							.productID(orderRequest.getProductID())
							.orderStatus("CREATED")
							.OrderDate(Instant.now())
							.build();
			
			repository.save(order);
			
			
			log.info("Calling payment service to complete the payment");
			
			PaymentRequest paymentReqeust = PaymentRequest.builder()
							.amount(orderRequest.getAmount())
							.orderID(order.getOrderID())
							.paymentMode(orderRequest.getPaymentMode().name())
							.build();
			try {
				paymentService.doPayment(paymentReqeust);
				log.info("Payment Done Successfully, Changing Order Status to PLACED");
				order.setOrderStatus("PLACED");
			} catch(Exception e) {
				log.error("Payment Got Failed ", e.getMessage());
				order.setOrderStatus("PAYMENT_FAILED");
			}
			
			log.info("Order placed successfully with order ID {} " + order.getOrderID());
			repository.save(order);
			return order.getOrderID();
			
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public OrderResponse getOrder(Long id) {
		
		Order order = repository.findById(id)
				.orElseThrow(() -> new CustomException("Order ID Not Found " + id,
				"NOT_FOUND",404));
		
		ProductResponse productResponse = restTemplate.getForObject(
				"http://localhost:8080/product/" + order.getProductID(), ProductResponse.class);
		
		PaymentResponse paymentResponse = restTemplate.getForObject(
				"http://localhost:8081/payment/"+order.getOrderID(), PaymentResponse.class);
	
		OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails
						.builder()
						.price(productResponse.getPrice())
						.quantity(productResponse.getQuantity())
						.productName(productResponse.getProductName())
						.productID(productResponse.getProductID())
						.build();
		
		OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails
						.builder()
						.amount(paymentResponse.getAmount())
						.orderId(paymentResponse.getOrderId())
						.paymentDate(paymentResponse.getPaymentDate())
						.paymentId(paymentResponse.getPaymentId())
						.status(paymentResponse.getStatus())
						.paymentMode(paymentResponse.getPaymentMode().name())
						.build();
		
		return OrderResponse
				.builder()
				.amount(order.getAmount())
				.OrderDate(order.getOrderDate())
				.orderStatus(order.getOrderStatus())
				.productID(order.getProductID())
				.quantity(order.getQuantity())
				.orderID(order.getOrderID())
				.productDetails(productDetails)
				.paymentDetails(paymentDetails)
				.build();
	}
}
