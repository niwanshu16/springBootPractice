package com.dailycode.ProductService.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailycode.ProductService.Entity.Product;
import com.dailycode.ProductService.Error.ProductServiceCustomException;
import com.dailycode.ProductService.Model.ProductRequest;
import com.dailycode.ProductService.Model.ProductResponse;
import com.dailycode.ProductService.Repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
	
	private ProductRepository repository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public long addProduct(ProductRequest request) {
		log.info("Adding Product...");
		Product product = Product.builder()
					.price(request.getPrice())
					.productName(request.getProductName())
					.quantity(request.getQuantity())
					.build();
		repository.save(product);
		
		log.info("Product Created");
		return product.getProductID();
		
	}

	@Override
	public ProductResponse getProduct(Long productID) {
		
		Product product = repository.findById(productID)
							.orElseThrow(() -> new ProductServiceCustomException("Product Not Found with ID " + productID, 
				"PRODUCT_NOT_FOUND"));
		
		ProductResponse productResponse = ProductResponse.builder()
											.price(product.getPrice())
											.quantity(product.getQuantity())
											.productName(product.getProductName())
											.productID(product.getProductID())
											.build();
		return productResponse;
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> products = repository.findAll();
		
		List<ProductResponse> response = products
								.stream().map((product) -> ProductResponse
												.builder()
												.price(product.getPrice())
												.productName(product.getProductName())
												.quantity(product.getQuantity())
												.productID(product.getProductID())
												.build())
								.collect(Collectors.toList());
		return response;
	}

	@Override
	public void reduceQuantity(Long id, Long quantity) {
		log.info("Reducing Quantity for Product ID : {}" + id);
		Product product = repository.findById(id).orElseThrow(() -> new ProductServiceCustomException("Product Not Found with ID "+ id, 
				"PRODUCT_NOT_FOUND"));
		if(product.getQuantity() < quantity) {
			throw new ProductServiceCustomException("Does not have Sufficient Quantity", "INSUFFIECIENT_PRODUCT_QUANTITY");
		}
		
		product.setQuantity(product.getQuantity() - quantity);
		repository.save(product);
		log.info("Reduced quantity successfully");
	}
	
	
}
