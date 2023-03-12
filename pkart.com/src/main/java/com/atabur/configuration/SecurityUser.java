package com.atabur.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.atabur.models.Admin;
import com.atabur.models.Customer;
import com.atabur.models.User;

public class SecurityUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private User users;

	public SecurityUser(User users) {
		super();
		this.users = users;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> grantedAuthos = new ArrayList<>();
		
		if(users instanceof Admin) {
			Admin admin = (Admin)users;
			SimpleGrantedAuthority grantAuth = new SimpleGrantedAuthority(admin.getRole());
			grantedAuthos.add(grantAuth);
			return grantedAuthos;
		}else {
			Customer customer = (Customer)users;
			SimpleGrantedAuthority grantAuth = new SimpleGrantedAuthority(customer.getRole());
			grantedAuthos.add(grantAuth);
			return grantedAuthos;
			
		}
	}

	@Override
	public String getPassword() {
		
		if(users instanceof Admin) {
			Admin admin = (Admin)users;
			return admin.getPassword();
		}else{
			Customer customer = (Customer)users;
			return customer.getPassword();
		}
	}

	@Override
	public String getUsername() {
		
		if(users instanceof Admin) {
			Admin admin = (Admin)users;
			return admin.getEmail();
		}else{
			Customer customer = (Customer)users;
			return customer.getEmail();
		}
			
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
