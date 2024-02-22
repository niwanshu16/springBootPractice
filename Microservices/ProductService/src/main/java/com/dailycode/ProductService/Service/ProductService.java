package com.dailycode.ProductService.Service;

import java.util.List;

import com.dailycode.ProductService.Model.ProductRequest;
import com.dailycode.ProductService.Model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest request);

	ProductResponse getProduct(Long productID);

	List<ProductResponse> getAllProducts();

	void reduceQuantity(Long id, Long quantity);

}
