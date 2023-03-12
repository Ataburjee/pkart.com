package com.atabur.services;

import java.util.List;


import com.atabur.exceptions.AdminException;
import com.atabur.exceptions.CustomerException;
import com.atabur.models.Admin;
import com.atabur.models.Customer;
import com.atabur.payload.request.LoginRequest;
import com.atabur.payload.responses.Message;


public interface AdminService {

	public Message registerAdmin(Admin admin) throws AdminException;

	public Message deleteAdmin(LoginRequest request) throws AdminException;

	public Message updateAdmin(Admin admin) throws AdminException;

	public List<Customer> getAllCustomers() throws CustomerException;

	public Customer getCustomerDetailsById(Long id) throws CustomerException;

}
