package com.atabur.configuration;
	
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.atabur.models.Admin;
import com.atabur.models.Customer;
import com.atabur.repositories.AdminRepository;
import com.atabur.repositories.CustomerRepository;
	
@Service
public class CustomUser implements UserDetailsService{
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Customer> opt = customerRepo.findByEmail(username);
		
		if(opt.isEmpty()) {
			
			Optional<Admin> opt2  = adminRepo.findByEmail(username);
			
			if(opt2.isEmpty()) throw new UsernameNotFoundException("User Does not exist");
			
			return new SecurityUser(opt2.get());
		}
		
		return new SecurityUser(opt.get());
	}

}
