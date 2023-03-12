package com.atabur.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atabur.exceptions.CustomerException;
import com.atabur.exceptions.OrderException;
import com.atabur.exceptions.PaymentsException;
import com.atabur.exceptions.ProductException;
import com.atabur.exceptions.ShipperException;
import com.atabur.models.Order;
import com.atabur.services.OrderService;

@RestController
@RequestMapping("/pkart.com")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@CrossOrigin
	@PostMapping({"/customer/order", "/admin/order"})
	public ResponseEntity<Order> BuyAProductHandler (@Valid @RequestBody Order order) throws OrderException, CustomerException, ShipperException, ProductException, PaymentsException{
		
		return new ResponseEntity<Order>(orderService.placeOrder(order), HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/admin/order/all")
	public ResponseEntity<List<Order>> getAllOrdersHandler () throws OrderException{
		
		List<Order> orders = orderService.getAllOrders();
		return new ResponseEntity<List<Order>>(orders,HttpStatus.FOUND);
	}
	
	
	@CrossOrigin
	@GetMapping("/admin/order/{customerID}")
	public ResponseEntity<List<Order>> getAllOrdersOfACustomerVHandler (@PathVariable Long customerID) throws OrderException, CustomerException{
		
		return new ResponseEntity<List<Order>>(orderService.getAllOrdersOfACustomer(customerID),HttpStatus.FOUND);
	}
	
	
	@CrossOrigin
	@GetMapping("/admin/order/{id}")
	public ResponseEntity<Order> getOrderDetailsByOrderIDVHandler (@PathVariable Long id) throws OrderException{
		
		return new ResponseEntity<Order>(orderService.getOrderDetailsByOrderID(id),HttpStatus.FOUND);
	}
	
	@CrossOrigin
	@GetMapping("/customer/orders/{id}")
	public ResponseEntity<List<Order>> getAllMyOrdersHandler (@PathVariable Long id) throws OrderException, CustomerException{
		
		List<Order> orders = orderService.getAllOrdersOfACustomer(id);
		
		return new ResponseEntity<List<Order>>(orders,HttpStatus.FOUND);
	}
	
}
