package com.atabur.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atabur.exceptions.CustomerException;
import com.atabur.exceptions.OrderException;
import com.atabur.exceptions.SupplierException;
import com.atabur.models.Supplier;
import com.atabur.payload.responses.SupplierResponse;
import com.atabur.services.SupplierService;

@RestController
@RequestMapping("/pkart.com")
public class SupplierController {
	
	@Autowired
	private SupplierService supplierService;

	@CrossOrigin
	@PostMapping("admin/supplier/register")
	public ResponseEntity<Supplier> registerSupplierHandler(@Valid @RequestBody Supplier suppliers) throws SupplierException{
		
		return new ResponseEntity<Supplier>(supplierService.addSupplier(suppliers),HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@PutMapping("admin/supplier/update")
	public ResponseEntity<Supplier> updateASupplierHandler(@Valid @RequestBody Supplier suppliers) throws SupplierException{
		
		return new ResponseEntity<Supplier>(supplierService.updateASupplier(suppliers),HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@DeleteMapping("admin/supplier/delete/{id}")
	public ResponseEntity<Supplier> deleteASupplierHandler(@Valid @RequestBody Long id) throws SupplierException{
		
		return new ResponseEntity<Supplier>(supplierService.deleteASupplier(id),HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("admin/supplier/all")
	public ResponseEntity<List<Supplier>> getAllSupplierHandler() throws SupplierException{
		
		return new ResponseEntity<List<Supplier>>(supplierService.getAllSuppliers(),HttpStatus.FOUND);
	}
	
	@CrossOrigin
	@GetMapping("admin/supplier/{id}")
	public ResponseEntity<Supplier> getASupplierByIDHandler(@PathVariable Long id) throws SupplierException{
		
		return new ResponseEntity<Supplier>(supplierService.getSupplierBySupplierID(id),HttpStatus.FOUND);
	}
	
	@CrossOrigin
	@GetMapping("customer/supplier/{customerID}/{shipperID}")
	public ResponseEntity<SupplierResponse> getASupplierByCustomerHandler(@PathVariable Long customerID, @PathVariable Long shipperID) throws SupplierException, CustomerException, OrderException{
		
		return new ResponseEntity<SupplierResponse>(supplierService.getSupplierDetailsByCustomer(customerID, shipperID),HttpStatus.FOUND);
	}
	
	@CrossOrigin
	@GetMapping("admin/supplier/name/{supplierName}")
	public ResponseEntity<Supplier> getAShipperBySupplierNameHandler(@PathVariable String supplierName) throws SupplierException {
		
		return new ResponseEntity<Supplier>(supplierService.getSupplierBySupplierName(supplierName),HttpStatus.FOUND);
	}
}
