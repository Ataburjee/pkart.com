package com.atabur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atabur.exceptions.ProductCategoryException;
import com.atabur.exceptions.ProductException;
import com.atabur.exceptions.SupplierException;
import com.atabur.models.Product;
import com.atabur.services.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService service;

	@CrossOrigin
	@PostMapping("admin/product/register")
	ResponseEntity<Product> addProduct(@RequestBody Product product) throws ProductException, ProductCategoryException, SupplierException{
		return new ResponseEntity<Product>(service.addProduct(product), HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@GetMapping({"/products","/","home"})
	ResponseEntity<List<Product>> getAllProduct() throws ProductException{
		return new ResponseEntity<List<Product>>(service.getAllProducts(), HttpStatus.FOUND);
	}
	
	@CrossOrigin
	@GetMapping({"/product/{name}"})
	ResponseEntity<List<Product>> getAllProductByName(@PathVariable String name) throws ProductException{
		return new ResponseEntity<List<Product>>(service.getProductsByProductName(name), HttpStatus.FOUND);
	}
	
	
	@CrossOrigin
	@GetMapping({"/product/{brand}"})
	ResponseEntity<List<Product>> getAllProductByBrandName(@PathVariable String brand) throws ProductException{
		return new ResponseEntity<List<Product>>(service.getProductsByBrandName(brand), HttpStatus.FOUND);
	}
	
	@CrossOrigin
	@GetMapping("admin/product/{id}")
	ResponseEntity<Product> getProductById(@PathVariable Long id) throws ProductException{
		return new ResponseEntity<Product>(service.getProductByID(id), HttpStatus.FOUND);
	}
	
	
	
	@CrossOrigin
	@GetMapping("product/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Product>> getProductsBetweenPrices(@PathVariable Double minPrice, @PathVariable Double maxPrice) throws ProductException {
        
        return new ResponseEntity<List<Product>>(service.getAllProductsBetweenThanAmounts(minPrice, maxPrice), HttpStatus.FOUND);
    }
	
	@CrossOrigin
	@GetMapping("product/{price}")
    public ResponseEntity<List<Product>> getProductsLessThanPrice(@PathVariable Double price) throws ProductException {
        
        return new ResponseEntity<List<Product>>(service.getAllProductsLessThanGivenAmount(price), HttpStatus.FOUND);
    }
	
}
