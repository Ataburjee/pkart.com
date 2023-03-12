package com.atabur.services;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atabur.models.Shipper;

public interface ShipperRepository extends JpaRepository<Shipper, Long>{

	Shipper findByCompanyName(String shipperName);

	boolean existsByCompanyName(@NotNull(message = "Company name can not be null ..!") String companyName);

	boolean existsByMobile(String mobile);

	boolean existsByEmail(String email);

}
