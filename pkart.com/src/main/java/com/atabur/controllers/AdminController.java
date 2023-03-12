package com.atabur.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atabur.configuration.SecurityUser;
import com.atabur.configuration.jwt.JwtUtils;
import com.atabur.exceptions.AdminException;
import com.atabur.exceptions.CustomerException;
import com.atabur.models.Admin;
import com.atabur.models.Customer;
import com.atabur.payload.request.LoginRequest;
import com.atabur.payload.responses.JwtResponse;
import com.atabur.payload.responses.Message;
import com.atabur.repositories.AdminRepository;
import com.atabur.repositories.CustomerRepository;
import com.atabur.services.AdminService;

@RestController
public class AdminController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private AdminService adminService;
	
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
		
	@CrossOrigin
	@PostMapping("/admin/signup")
	public ResponseEntity<Message> registerAdminHandler(@Valid @RequestBody Admin admin) throws AdminException{
	
		return new ResponseEntity<Message>(adminService.registerAdmin(admin),HttpStatus.ACCEPTED);	
	}
	
	
	@CrossOrigin
	@PostMapping("/user/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
	    
	    Optional<Admin> optAdmin = adminRepository.findByEmail(loginRequest.getUsername());
	    String user;
		if(optAdmin.isPresent()) { 
			user=optAdmin.get().getRole();
		}
	    else {
	    	Optional<Customer> optCustomer = customerRepository.findByEmail(loginRequest.getUsername());
	    	user = optCustomer.get().getRole();
	    }

	    return ResponseEntity.ok(new JwtResponse (jwt, "Bearer", userDetails.getUsername(), user));
	    
	  }

	@CrossOrigin
	@PutMapping("/admin/update")
	public ResponseEntity<Message> updateAdminHandler(@Valid @RequestBody Admin admin) throws AdminException{
		
		Message message = adminService.updateAdmin(admin);
		
		return new ResponseEntity<Message>(message,HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@DeleteMapping("/admin/delete")
	public ResponseEntity<Message> deleteAdminHandler (@RequestBody LoginRequest request) throws AdminException{
		
		Message message = adminService.deleteAdmin(request);
		
		return new ResponseEntity<Message>(message,HttpStatus.ACCEPTED);
	}
	
	
	@CrossOrigin
	@GetMapping("/admin/customer")
	public ResponseEntity<List<Customer>> getCustomersHandler () throws CustomerException{
		
		List<Customer> customers = adminService.getAllCustomers();
		
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.FOUND);
	}
	
	@CrossOrigin
	@GetMapping("/admin/customer/{id}")
	public ResponseEntity<Customer> getCustomerHandler (@PathVariable Long id) throws CustomerException{
		
		Customer customer = adminService.getCustomerDetailsById(id);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.FOUND);
	}
	
}
