package com.atabur.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atabur.exceptions.CustomerException;
import com.atabur.exceptions.OrderException;
import com.atabur.exceptions.SupplierException;
import com.atabur.models.Supplier;
import com.atabur.payload.responses.SupplierResponse;

@Service
public class SupplierServiceImpl implements SupplierService{
	
	@Autowired
	private SupplierRepository supplierRepository;
	

	@Override
	public List<Supplier> getAllSuppliers() throws SupplierException {
		List<Supplier> suppliers =  supplierRepository.findAll();
		if(suppliers!=null) return suppliers;
		else throw new SupplierException("No suppliers available...!");
	}

	@Override
	public Supplier getSupplierBySupplierName(String supplierName) throws SupplierException {
		Supplier suppliers =  supplierRepository.findByCompanyName(supplierName);
		if(suppliers!=null) return suppliers;
		else throw new SupplierException("suppliers not available with the name "+supplierName);
	}

	@Override
	public Supplier getSupplierBySupplierID(Long id) throws SupplierException {
		return supplierRepository.findById(id).orElseThrow(() -> new SupplierException("Supplier not available with the id "+id));
	}

	@Override
	public Supplier addSupplier(Supplier suppliers) throws SupplierException {
		
		if(supplierRepository.existsByCompanyName(suppliers.getCompanyName())) throw new SupplierException("Company already there...!");
		if(supplierRepository.existsByPhone(suppliers.getPhone())) throw new SupplierException("Company mobile no already there...!");
		if(supplierRepository.existsByEmail(suppliers.getEmail())) throw new SupplierException("Company email already there...!");
		return supplierRepository.save(suppliers);
	}

	@Override
	public Supplier updateASupplier(Supplier suppliers) throws SupplierException {
		
		if(!supplierRepository.existsById(suppliers.getId())) throw new SupplierException("Supplier not available with the id "+suppliers.getId());
		supplierRepository.deleteById(suppliers.getId());
		
		if(supplierRepository.existsByCompanyName(suppliers.getCompanyName())) throw new SupplierException("Company already there...!");
		if(supplierRepository.existsByPhone(suppliers.getPhone())) throw new SupplierException("Company mobile no already there...!");
		if(supplierRepository.existsByEmail(suppliers.getEmail())) throw new SupplierException("Company email already there...!");
		return supplierRepository.save(suppliers);
	}

	@Override
	public Supplier deleteASupplier(Long supplierID) throws SupplierException {
		
		if(!supplierRepository.existsById(supplierID)) throw new SupplierException("Supplier not available with the id "+supplierID);
		Optional<Supplier> shOptional = supplierRepository.findById(supplierID);
		supplierRepository.deleteById(supplierID);
		return shOptional.get();
	}

	@Override
	public SupplierResponse getSupplierDetailsByCustomer(Long customerID, Long supplierID)
			throws SupplierException, CustomerException, OrderException {
		
		if(!supplierRepository.existsById(supplierID)) throw new SupplierException("supplier not available with the id '"+supplierID+"'");
		//Optional<Order> optional = orderRepository.findById(supplierID);
		
//		if(!optional.get().getCustomer().getId().equals(customerID)) throw new CustomerException("Customer order is not assotiated with the shipper");
		Optional<Supplier> optional2 = supplierRepository.findById(supplierID);
		return new SupplierResponse(optional2.get().getCompanyName(), optional2.get().getPhone(), optional2.get().getEmail(), optional2.get().getCountry());
		
	}

}
