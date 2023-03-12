package com.atabur.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atabur.exceptions.ProductCategoryException;
import com.atabur.models.Category;
import com.atabur.repositories.CategoryRepo;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
	
	@Autowired
	private CategoryRepo prCategoryRepo;

	@Override
	public List<Category> getAllProductCategory() throws ProductCategoryException {
		
		List<Category> categories = prCategoryRepo.findAll();
		if(categories.isEmpty()) throw new ProductCategoryException("No category exists...!");
		return categories;
		
	}

	@Override
	public List<Category> getAllActiveProductCategory() throws ProductCategoryException {
		
		List<Category> activeProduct = prCategoryRepo.getAllActiveProducts();
		if(activeProduct.isEmpty()) throw new ProductCategoryException("No active products exists...!");
		return activeProduct;
		
	}

	@Override
	public List<Category> getAllInactiveProductCategory() throws ProductCategoryException {
		
		List<Category> inactiveProduct = prCategoryRepo.getAllInactiveProducts();
		if(inactiveProduct.isEmpty()) throw new ProductCategoryException("No Inactive products exists...!");
		return inactiveProduct;
		
	}

	@Override
	public Category addCategory(Category productCategoryDetails) throws ProductCategoryException {
		
		if(prCategoryRepo.existsById(productCategoryDetails.getId())) throw new ProductCategoryException("Product category already exist...!");
		if(prCategoryRepo.existsByCategoryName(productCategoryDetails.getCategoryName())) throw new ProductCategoryException("Product category already exist...!");
		
		return prCategoryRepo.save(productCategoryDetails);
		
	}

	@Override
	public Category deleteCategory(Long categoryID) throws ProductCategoryException {
		
		if(!prCategoryRepo.existsById(categoryID)) throw new ProductCategoryException("Product category does not exist...!");
		Category productCategory = prCategoryRepo.findById(categoryID).get();
		prCategoryRepo.delete(productCategory);
		return productCategory;
	}

	@Override
	public Category updateCategory(Category updatedProductCategoryDetails)
			throws ProductCategoryException {
		if(!prCategoryRepo.existsById(updatedProductCategoryDetails.getId())) throw new ProductCategoryException("Product category does not exist...!");
		Category productCategory = prCategoryRepo.findById(updatedProductCategoryDetails.getId()).get();
		return prCategoryRepo.save(productCategory);
	}

}
