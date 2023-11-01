package com.dailycode.OrderService.Service;


import com.dailycode.OrderService.Model.OrderRequest;
import com.dailycode.OrderService.Model.OrderResponse;

public interface OrderService {

	Long placeOrder(OrderRequest orderRequest);

	OrderResponse getOrder(Long id);

}
