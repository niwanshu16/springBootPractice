package com.dailycode.OrderService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dailycode.OrderService.Entity.Order;
import com.dailycode.OrderService.Exception.CustomException;
import com.dailycode.OrderService.External.Client.PaymentService;
import com.dailycode.OrderService.External.Client.ProductService;
import com.dailycode.OrderService.External.Request.PaymentMode;
import com.dailycode.OrderService.External.Request.PaymentRequest;
import com.dailycode.OrderService.External.Request.PaymentResponse;
import com.dailycode.OrderService.External.Request.ProductResponse;
import com.dailycode.OrderService.Model.OrderRequest;
import com.dailycode.OrderService.Model.OrderResponse;
import com.dailycode.OrderService.Repository.OrderRepository;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
@SpringBootTest
public class OrderServiceImplTest {
	
	@Mock
	private OrderRepository repository;
	
	@Mock
	private ProductService productService;
	
	@Mock
	private PaymentService paymentService;
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	OrderService orderService = new OrderServiceImpl(repository);
	
	@DisplayName("Invoking Get Order -Successfully")
	@Test
	void test_When_Order_Success() {
		Order order = getMockOrder();
		
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(order));
		
		when(restTemplate.getForObject(
				"http://localhost:8080/product/" + order.getProductID(), ProductResponse.class)).thenReturn(getMockProductResponse());
		when(restTemplate.getForObject(
				"http://localhost:8081/payment/"+order.getOrderID(), PaymentResponse.class)).thenReturn(getMockPaymentResponse());
		
		OrderResponse orderResponse = orderService.getOrder((long) 1);
		
		verify(repository,times(1)).findById(Mockito.anyLong());
		verify(restTemplate,times(1)).getForObject(
				"http://localhost:8080/product/" + order.getProductID(), ProductResponse.class);
		verify(restTemplate,times(1)).getForObject(
				"http://localhost:8081/payment/"+order.getOrderID(), PaymentResponse.class);
				
		Assertions.assertNotNull(orderResponse);
		Assertions.assertEquals(order.getOrderID(), orderResponse.getOrderID());
	}
	
	@DisplayName("ORDER FAILURE - NOT FOUND")
	@Test
	void test_When_Order_NOT_FOUND() {
		
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		
		CustomException exception = Assertions.assertThrows(CustomException.class, () -> orderService.getOrder((long) 1));
		
		Assertions.assertEquals("NOT_FOUND",exception.getErrorCode());
		Assertions.assertEquals(404, exception.getStatus());
		
		verify(repository,times(1)).findById(Mockito.anyLong());
		
	}
	
	@DisplayName("Place Order - Success")
	@Test
	void test_place_order_success() {
		
		OrderRequest orderRequest = getMockOrderRequest();
		Order order = getMockOrder();
		
		when(repository.save(Mockito.any(Order.class)))
			.thenReturn(order);
		when(productService.reduceQuantity(Mockito.anyLong(), Mockito.anyLong()))
			.thenReturn(new ResponseEntity<>(HttpStatus.OK));
		when(paymentService.doPayment(Mockito.any(PaymentRequest.class)))
			.thenReturn(new ResponseEntity<>(2L,HttpStatus.OK));
		
		long orderId = orderService.placeOrder(orderRequest);
		
		verify(repository,times(2)).save(Mockito.any());
		verify(paymentService,times(1)).doPayment(Mockito.any(PaymentRequest.class));
		verify(productService,times(1)).reduceQuantity(Mockito.anyLong(), Mockito.anyLong());
		
		Assertions.assertEquals(orderId, order.getOrderID());
	}
	
	
	private PaymentRequest getMockPaymentReqeust() {
		// TODO Auto-generated method stub
		return PaymentRequest
				.builder()
				.amount(10000L)
				.orderID(2L)
				.paymentMode("CASH")
				.referenceNumber("233")
				.build();
	}

	private Order getMockOrder() {
		Order order = Order
					.builder()
					.amount(1000)
					.orderID(2L)
					.OrderDate(Instant.now())
					.orderStatus("PLACED")
					.quantity(2L)
					.productID(1L)
					.build();
		return order;
	}
	
	private PaymentResponse getMockPaymentResponse() {
		return PaymentResponse
				.builder()
				.amount(1200L)
				.paymentDate(Instant.now())
				.paymentMode(PaymentMode.CASH)
				.paymentId(2L)
				.orderId(2L)
				.status("SUCCESS")
				.build();
	}
	
	private ProductResponse getMockProductResponse() {
		return ProductResponse
				.builder()
				.price((long)1000)
				.quantity((long)12)
				.productName("KAK")
				.build();
	}
	
	private OrderRequest getMockOrderRequest() {
		return OrderRequest
					.builder()
					.amount(1000L)
					.paymentMode(PaymentMode.CASH)
					.productID(152L)
					.quantity(1L)
					.build();
	}
}
