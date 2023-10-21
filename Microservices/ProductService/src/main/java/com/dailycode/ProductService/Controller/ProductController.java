package com.dailycode.ProductService.Controller;

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

import com.dailycode.ProductService.Model.ProductRequest;
import com.dailycode.ProductService.Model.ProductResponse;
import com.dailycode.ProductService.Service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService service) {
		this.productService = service;
	}
	
	@PostMapping
	public ResponseEntity<Long> addProduct(@RequestBody ProductRequest request) {
		long productID = productService.addProduct(request);
		
		return new ResponseEntity<>(productID,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") Long productID) {
		ProductResponse productResponse = productService.getProduct(productID);
		return new ResponseEntity<>(productResponse,HttpStatus.FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		List<ProductResponse> products = productService.getAllProducts();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable("id") Long id, @RequestParam Long quantity) {
		
		productService.reduceQuantity(id,quantity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
