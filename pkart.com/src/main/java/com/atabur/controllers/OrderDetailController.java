package com.atabur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atabur.exceptions.OrderDetailsException;
import com.atabur.exceptions.OrderException;
import com.atabur.models.OrderDetail;
import com.atabur.services.OrderDetailsService;

@RestController
@RequestMapping("/pkart.com")
public class OrderDetailController {
	
	@Autowired
	OrderDetailsService service;
	
	@GetMapping("/admin/orderdetail/all")
	ResponseEntity<List<OrderDetail>> getAllPaymentHandler() throws OrderDetailsException{
		return new ResponseEntity<List<OrderDetail>>(service.getAllOrderDetail(),HttpStatus.FOUND);
	}
	
//	@GetMapping("/customer/orderdetail/{cid}/{oid}")
//	ResponseEntity<OrderDetail> getPaymentByIdHandler(@PathVariable Long cid, @PathVariable Long oid) throws OrderDetailsException, OrderException, CustomerException {
//		return new ResponseEntity<OrderDetail>(service.getMyOrderDetails(cid, oid),HttpStatus.FOUND);
//	}
	
	@GetMapping("/admin/orderdetail/{id}")
	ResponseEntity<OrderDetail> getPaymentByIdHandler(@PathVariable Integer id) throws OrderDetailsException, OrderException{
		return new ResponseEntity<OrderDetail>(service.getAPerticulerOrderDetails(id),HttpStatus.FOUND);
	}
	
	
}
