package com.atabur.services;

import java.util.List;

import com.atabur.exceptions.ProductCategoryException;
import com.atabur.exceptions.ProductException;
import com.atabur.exceptions.SupplierException;
import com.atabur.models.Product;

public interface ProductService {
	
	Product addProduct(Product product) throws ProductException, ProductCategoryException, SupplierException;
	
	List<Product> getAllProducts() throws ProductException;
	
	List<Product> getProductsByProductName(String productName) throws ProductException;
	
	List<Product> getProductsByBrandName(String brandName) throws ProductException;
	
	Product getProductByID(Long productID) throws ProductException;
	
	List<Product> getAllProductsLessThanGivenAmount(Double amount) throws ProductException;
	
	List<Product> getAllProductsBetweenThanAmounts(Double amount1, Double amount2) throws ProductException;
}
