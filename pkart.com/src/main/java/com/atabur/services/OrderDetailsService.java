package com.atabur.services;

import java.util.List;

import com.atabur.exceptions.OrderDetailsException;
import com.atabur.exceptions.OrderException;
import com.atabur.models.OrderDetail;

public interface OrderDetailsService {

	List<OrderDetail> getAllOrderDetail() throws OrderDetailsException;
	
//	OrderDetail getMyOrderDetails(Long customerID, Long OrderID) 
//			throws OrderDetailsException, OrderException, CustomerException;
//	
	OrderDetail getAPerticulerOrderDetails(Integer id) throws OrderDetailsException, OrderException;
	
	
}
