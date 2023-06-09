package com.atabur.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atabur.models.Order;

public interface OrderRepository  extends JpaRepository<Order, Long>{

	@Query(value = "SELECT * FROM orders WHERE customerid = ?1", nativeQuery = true)
	List<Order> getOrderByCustomerId(Integer customerId);

	List<Order> findByCustomerid(Long customerID);

	
}
