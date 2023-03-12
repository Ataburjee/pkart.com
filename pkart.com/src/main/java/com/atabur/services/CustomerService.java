package com.atabur.services;

import javax.validation.Valid;

import com.atabur.exceptions.CustomerException;
import com.atabur.models.Customer;
import com.atabur.payload.request.LoginRequest;
import com.atabur.payload.responses.Message;

public interface CustomerService {

	Message registerCustomer(Customer customer) throws CustomerException;

	Message updateCustomer(@Valid Customer customer) throws CustomerException;

	Message deleteCustomer(LoginRequest loginRequest) throws CustomerException;
	
	Customer deleteCustomerByID(Long customerID) throws CustomerException;
	
}
