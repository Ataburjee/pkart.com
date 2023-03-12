package com.atabur.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atabur.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

	Supplier findByCompanyName(String supplierName);

	boolean existsByCompanyName(String companyName);

	boolean existsByPhone(String phone);

	boolean existsByEmail(String email);

}
