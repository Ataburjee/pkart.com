package com.atabur.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atabur.exceptions.OrderDetailsException;
import com.atabur.exceptions.OrderException;
import com.atabur.models.OrderDetail;
import com.atabur.repositories.OrderDetailRepo;
import com.atabur.repositories.OrderRepository;

@Service
public class OrderDetailsServiceIpml implements OrderDetailsService{
	
	@Autowired
	OrderDetailRepo ordDetailsRepository;
	
	@Autowired
	OrderRepository orderRepo;

	@Override
	public List<OrderDetail> getAllOrderDetail() throws OrderDetailsException {
		List<OrderDetail> orderDetails = ordDetailsRepository.findAll();
		if(orderDetails.isEmpty()) throw new OrderDetailsException("No order found...!");
		return orderDetails;
	}

	@Override
	public OrderDetail getAPerticulerOrderDetails(Integer orderDetailID) throws OrderDetailsException, OrderException {
		return ordDetailsRepository.findById(orderDetailID)
				.orElseThrow(() -> new OrderDetailsException
						("Order does not found wih the ortderID "+orderDetailID));
		
	}

}
