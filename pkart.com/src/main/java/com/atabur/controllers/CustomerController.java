package com.atabur.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atabur.configuration.jwt.JwtUtils;
import com.atabur.exceptions.CustomerException;
import com.atabur.models.Customer;
import com.atabur.payload.request.LoginRequest;
import com.atabur.payload.responses.Message;
import com.atabur.services.CustomerService;



@RestController
@RequestMapping("/pkart.com")
public class CustomerController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private CustomerService customerService;
	
	
	@CrossOrigin
	@PostMapping("/customer/signup")
	public ResponseEntity<Message> registerCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException{
		
		Message cust = customerService.registerCustomer(customer);
		
		return new ResponseEntity<Message>(cust,HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@PutMapping("/customer/update")
	public ResponseEntity<Message> updateCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException{
		
		Message msg = customerService.updateCustomer(customer);
		
		return new ResponseEntity<Message>(msg,HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@DeleteMapping("/customer/delete")
	public ResponseEntity<Message> deleteCustomerHandler (@RequestBody LoginRequest request) throws CustomerException{
		
		Message message = customerService.deleteCustomer(request);
		
		return new ResponseEntity<Message>(message,HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@DeleteMapping("/admin/customer/delete/{id}")
	public ResponseEntity<Customer> deleteAdminCustomerHandler (@PathVariable Long id) throws CustomerException{
		
		Customer message = customerService.deleteCustomerByID(id);
		
		return new ResponseEntity<Customer>(message,HttpStatus.ACCEPTED);
	}
	
	
}