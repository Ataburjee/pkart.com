package com.atabur.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atabur.exceptions.AdminException;
import com.atabur.exceptions.CustomerException;
import com.atabur.models.Admin;
import com.atabur.models.Customer;
import com.atabur.models.StrongPassword;
import com.atabur.payload.request.LoginRequest;
import com.atabur.payload.responses.Message;
import com.atabur.repositories.AdminRepository;
import com.atabur.repositories.CustomerRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public Message registerAdmin(Admin admin) throws AdminException {
		
		if (admin == null)
			throw new AdminException("Enter Valid Admin Details..!");
		
		if(adminRepository.findByEmail(admin.getEmail()).isPresent() || customerRepository.findByEmail(admin.getEmail()).isPresent()) 
			throw new AdminException("Email '"+admin.getEmail()+"' already exists...!");
		
		if(adminRepository.findByMobile(admin.getMobile()).isPresent() || customerRepository.findByMobile(admin.getMobile()).isPresent()) 
			throw new AdminException("Mobile no '"+admin.getMobile()+"' already exists...!");
		
		if(!StrongPassword.ifContainCapitalLetter(admin.getPassword()) || 
				!StrongPassword.ifContainNum(admin.getPassword()) || 
				!StrongPassword.ifContainSmallLetter(admin.getPassword()) || 
				!StrongPassword.ifContainSpacialChar(admin.getPassword())) throw new AdminException("Password must contain atleast 1 capital, 1 small, 1 number & 1 spacial character...!");
		
		if(!admin.getPassword().equals(admin.getConfirm_password())) throw new AdminException("Password does not match...!");
		
		admin.setPassword(encoder.encode(admin.getPassword()));
		
		adminRepository.save(admin);
		
		return new Message("Account created succesfully with username '" + admin.getEmail());
	}
	
	@Override
	public Message deleteAdmin(LoginRequest request) throws AdminException {
		
		Optional<Admin> opt = adminRepository.findByEmail(request.getUsername());
		
		if (opt.isEmpty())
			throw new AdminException("Enter Valid Admin Email..!");
		
		if (opt.get().getConfirm_password().equals(request.getPassword()))
			adminRepository.deleteById(opt.get().getId());
		else
			throw new AdminException("Enter Valid Admin Password..!");
		
		return new Message("Delete Sucessfully with Id " + opt.get().getId());
		
	}

	@Override
	public Message updateAdmin(Admin admin) throws AdminException {
		
		Optional<Admin> opt = adminRepository.findById(admin.getId());
		
		if (opt.isEmpty())
			throw new AdminException("Invalid Admin details");
		
		adminRepository.save(admin);
		
		return new Message("Updated Sucessfully with Id " + admin.getId());
		
	}

	@Override
	public List<Customer> getAllCustomers() throws CustomerException {

		List<Customer> customers = customerRepository.findAll();

		if (customers.size() == 0)
			throw new CustomerException("NO Customer Available...!");

		return customers;
	}

	@Override
	public Customer getCustomerDetailsById(Long id) throws CustomerException {

		Optional<Customer> customer = customerRepository.findById(id);

		if (customer.isEmpty())
			throw new CustomerException("Wrong customer id");

		return customer.get();
	}
	
}