package com.atabur.services;

import java.util.List;

import com.atabur.exceptions.ProductCategoryException;
import com.atabur.models.Category;

public interface ProductCategoryService {
	
	Category addCategory(Category productCategoryDetails) throws ProductCategoryException;
	
	Category deleteCategory(Long categoryID) throws ProductCategoryException;
	
	Category updateCategory(Category updatedProductCategoryDetails) throws ProductCategoryException;

	List<Category> getAllProductCategory() throws ProductCategoryException;
	
	List<Category> getAllActiveProductCategory() throws ProductCategoryException;
	
	List<Category> getAllInactiveProductCategory() throws ProductCategoryException;
	
}
