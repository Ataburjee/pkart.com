package com.atabur.services;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.atabur.models.Product;
//import com.atabur.repositories.ProductRepo;
//
//@Service
//public class ProductServiceImpl implements ProductService{
//	
//	@Autowired
//	private ProductRepo repo;
//
//	@Override
//	public Product addproduct(Product product) {
//		return repo.save(product);
//	}
//
//}



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atabur.exceptions.ProductCategoryException;
import com.atabur.exceptions.ProductException;
import com.atabur.exceptions.SupplierException;
import com.atabur.models.Product;
import com.atabur.repositories.CategoryRepo;
import com.atabur.repositories.ProductRepo;
import com.atabur.repositories.SupplierRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepository;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private SupplierRepo sRepo;	
	
	@Override
	public List<Product> getAllProducts() throws ProductException {
		List<Product> products =  productRepository.findAll();
		if(products!=null) return products;
		else throw new ProductException("No products available...!");
	}

	@Override
	public List<Product> getProductsByProductName(String productName) throws ProductException {
		List<Product> products = productRepository.findProductsByNameContaining(productName);
		if(products!=null) return products;
		else throw new ProductException("No products available...!");
	}

	@Override
	public List<Product> getProductsByBrandName(String brandName) throws ProductException {
		List<Product> products = productRepository.findProductsByBrandNameContaining(brandName);
		if(products!=null) return products;
		else throw new ProductException("No products available...!");
	}

	@Override
	public Product getProductByID(Long productID) throws ProductException {
		return (productRepository.findById(productID))
				.orElseThrow(() -> new ProductException("No such product found by the id "+productID));
	}

	@Override
	public Product addProduct(Product productDetails) throws ProductException, ProductCategoryException, SupplierException {
		
		if(productRepository.existsById(productDetails.getId()))
			throw new ProductException("Product already exist with the id "+productDetails.getId());
		
		if(!categoryRepo.existsById(productDetails.getCategoryid()))
			throw new ProductCategoryException("category does not exists...!");
		
		if(!sRepo.existsById(productDetails.getSupplierid()))
			throw new SupplierException("Supplier does not exixst...!");
		
		return productRepository.save(productDetails);
	}

	

	@Override
	public List<Product> getAllProductsBetweenThanAmounts(Double minPrice, Double maxPrice) throws ProductException {
		
		return productRepository.findBySalePriceBetween(minPrice, maxPrice);
	}

	@Override
	public List<Product> getAllProductsLessThanGivenAmount(Double amount) throws ProductException {
		
		return productRepository.findBySalePriceLessThan(amount);
	}
	

}
