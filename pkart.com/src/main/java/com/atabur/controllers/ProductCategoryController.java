package com.atabur.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atabur.exceptions.ProductCategoryException;
import com.atabur.models.Category;
import com.atabur.services.ProductCategoryService;

@RestController
@RequestMapping("/admin/category")
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryService productCategoryService;

	@PostMapping("/register")
	public ResponseEntity<Category> addCategoryHandler(@Valid @RequestBody Category productCategory) throws ProductCategoryException{
		
		return new ResponseEntity<Category>(productCategoryService.addCategory(productCategory), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Category> deleteCategoryHandler(@Valid @PathVariable Long id) throws ProductCategoryException{
		
		return new ResponseEntity<Category>(productCategoryService.deleteCategory(id), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Category> updateCategoryHandler(@Valid @RequestBody Category productCategory) throws ProductCategoryException{
		
		return new ResponseEntity<Category>(productCategoryService.updateCategory(productCategory), HttpStatus.OK);
	}
	
	@GetMapping({"/all","/"})
	public ResponseEntity<List<Category>> getAllCategoryHandler() throws ProductCategoryException{
		
		return new ResponseEntity<List<Category>>(productCategoryService.getAllProductCategory(), HttpStatus.FOUND);
	}
	
	@GetMapping("/active")
	public ResponseEntity<List<Category>> getAllActiveCategoryHandler() throws ProductCategoryException{
		
		return new ResponseEntity<List<Category>>(productCategoryService.getAllActiveProductCategory(), HttpStatus.FOUND);
	}
	
	@GetMapping("/inactive")
	public ResponseEntity<List<Category>> getAllInctiveCategoryHandler() throws ProductCategoryException{
		
		return new ResponseEntity<List<Category>>(productCategoryService.getAllInactiveProductCategory(), HttpStatus.FOUND);
	}
	
}
