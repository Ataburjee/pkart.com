package com.atabur.services;

import java.util.List;

import com.atabur.exceptions.CustomerException;
import com.atabur.exceptions.OrderException;
import com.atabur.exceptions.SupplierException;
import com.atabur.models.Supplier;
import com.atabur.payload.responses.SupplierResponse;

public interface SupplierService {

	Supplier addSupplier(Supplier suppliers) throws SupplierException;
	
	Supplier updateASupplier(Supplier suppliers) throws SupplierException;
	
	Supplier deleteASupplier(Long supplierID) throws SupplierException;
	
	SupplierResponse getSupplierDetailsByCustomer(Long customerID,Long supplierID) throws SupplierException, CustomerException, OrderException;
	
	List<Supplier> getAllSuppliers() throws SupplierException;
	
	Supplier getSupplierBySupplierName(String supplierName) throws SupplierException;
	
	Supplier getSupplierBySupplierID(Long id) throws SupplierException;
	
	
}
