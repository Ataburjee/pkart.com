
package com.atabur.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atabur.exceptions.*;
import com.atabur.models.*;
import com.atabur.payload.request.LoginRequest;
import com.atabur.payload.responses.Message;
import com.atabur.repositories.*;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	PasswordEncoder encoder;
	

	@Override
	public Message registerCustomer(Customer customer) throws CustomerException {

		if (customer == null)
			throw new CustomerException("Enter Valid Customer....!");
		
		if(customerRepo.findByEmail(customer.getEmail()).isPresent() || adminRepository.findByEmail(customer.getEmail()).isPresent()) 
			throw new CustomerException("Email '"+customer.getEmail()+"' already exists...!");
		
		if(customerRepo.findByMobile(customer.getMobile()).isPresent() || adminRepository.findByMobile(customer.getMobile()).isPresent()) 
			throw new CustomerException("Mobile no '"+customer.getMobile()+"' already exists...!");
		
		if (!customer.getPassword().equals(customer.getConfirm_password())) {
            throw new CustomerException("Passwords don't match...!");
        }

		customer.setPassword(encoder.encode(customer.getPassword()));

		customerRepo.save(customer);
		return new Message("Customer registered succesfully with username '" + customer.getEmail()+"'");
	}

	@Override
	public Message updateCustomer(Customer customer) throws CustomerException {

		if (customer == null)
			throw new CustomerException("Enter Valid Customer Details....!");

		Optional<Customer> opt = customerRepo.findByEmail(customer.getEmail());
		
		customer.setId(opt.get().getId());
		
		if (opt.isEmpty())
			throw new CustomerException("Enter Valid Email...!");

			customerRepo.save(customer);

		return new Message("Updated Sucessfully with Id " + opt.get().getId());

	}

	@Override
	public Message deleteCustomer(LoginRequest request) throws CustomerException {

		Optional<Customer> opt = customerRepo.findByEmail(request.getUsername());

		if (opt.isEmpty())
			throw new CustomerException("Enter Valid Email...!");

		if (opt.get().getConfirm_password().equals(request.getPassword()))
			customerRepo.deleteById(opt.get().getId());
		else
			throw new CustomerException("Enter Valid User Password...!");

		return new Message("Delete Sucessfully with Id " + opt.get().getId());
	}

	@Override
	public Customer deleteCustomerByID(Long customerID) throws CustomerException {
		customerRepo.findById(customerID).orElseThrow(() -> new CustomerException("Enter Valid customer id...!"));
		Customer customer = customerRepo.findById(customerID).get();
		customerRepo.deleteById(customerID);
		return customer;
	}


}
