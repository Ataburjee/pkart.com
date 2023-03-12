package com.atabur.repositories;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atabur.models.Admin;
import com.atabur.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmail(String email);

	Optional<Admin> findByMobile(@NotNull String mobile);
	
}
