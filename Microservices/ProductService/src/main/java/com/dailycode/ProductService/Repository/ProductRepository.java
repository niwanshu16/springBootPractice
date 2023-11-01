package com.dailycode.ProductService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycode.ProductService.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

}
