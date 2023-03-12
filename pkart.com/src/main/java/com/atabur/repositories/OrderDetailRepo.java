package com.atabur.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atabur.models.OrderDetail;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer>{

	Optional<OrderDetail> findById(Integer orderDetailID);

}
