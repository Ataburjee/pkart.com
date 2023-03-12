package com.atabur.services;

import java.util.List;

import com.atabur.exceptions.CustomerException;
import com.atabur.exceptions.OrderException;
import com.atabur.exceptions.PaymentsException;
import com.atabur.exceptions.ProductException;
import com.atabur.exceptions.ShipperException;
import com.atabur.models.Order;

public interface OrderService {
	
	List<Order> getAllOrders() throws OrderException;
	
	Order getOrderDetailsByOrderID(Long orderID) throws OrderException;
	
	List<Order> getAllOrdersOfACustomer(Long customerID) throws CustomerException, OrderException;
	
	List<Order> getAllMyOrders(Long customerID) throws OrderException, CustomerException;
	
	Order placeOrder(Order order) throws CustomerException, ProductException, ShipperException, PaymentsException;
	
}
