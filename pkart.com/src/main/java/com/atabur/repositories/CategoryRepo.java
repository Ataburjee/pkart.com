package com.atabur.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atabur.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

	@Query(value = "SELECT * FROM product_categories WHERE status = 'Active'", nativeQuery = true)
	List<Category> getAllActiveProducts();

	@Query(value = "SELECT * FROM product_categories WHERE status = 'Inactive'", nativeQuery = true)
	List<Category> getAllInactiveProducts();

	boolean existsByCategoryName(String categoryName);

}
