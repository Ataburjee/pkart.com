package com.atabur.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atabur.models.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

	@Query("SELECT p FROM Product p WHERE p.productName LIKE %:productname%")
    List<Product> findProductsByNameContaining(String productname);

	@Query("SELECT p FROM Product p WHERE p.brandName LIKE %:brandname%")
	List<Product> findProductsByBrandNameContaining(String brandname);
	
	List<Product> findBySalePriceLessThan(double salePrice);
	
	List<Product> findBySalePriceBetween(double minPrice, double maxPrice);
}
