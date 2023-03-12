package com.atabur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atabur.models.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Long>{

}
