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

import com.atabur.exceptions.ShipperException;
import com.atabur.models.Shipper;
import com.atabur.services.ShipperService;

@RestController
@RequestMapping("/pkart.com")
public class ShipperController {
	
	@Autowired
	private ShipperService shipperService;

	@CrossOrigin
	@PostMapping("admin/shipper/register")
	public ResponseEntity<Shipper> registerShipperHandler(@Valid @RequestBody Shipper shippers) throws ShipperException{
		
		return new ResponseEntity<Shipper>(shipperService.addShipper(shippers),HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@PutMapping("admin/shipper/update")
	public ResponseEntity<Shipper> updateAShipperHandler(@Valid @RequestBody Shipper shippers) throws ShipperException{
		
		return new ResponseEntity<Shipper>(shipperService.updateAShipper(shippers),HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@DeleteMapping("admin/shipper/delete/{id}")
	public ResponseEntity<Shipper> deleteAShipperHandler(@Valid @PathVariable Long id) throws ShipperException{
		
		return new ResponseEntity<Shipper>(shipperService.deleteAShipper(id),HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("admin/shipper/all")
	public ResponseEntity<List<Shipper>> getShipperHandler() throws ShipperException{
		
		return new ResponseEntity<List<Shipper>>(shipperService.getAllShippers(),HttpStatus.FOUND);
	}
	
	@CrossOrigin
	@GetMapping("admin/shipper/{id}")
	public ResponseEntity<Shipper> getAShipperByIDHandler(@PathVariable Long id) throws ShipperException{
		
		return new ResponseEntity<Shipper>(shipperService.getShipperByShipperID(id),HttpStatus.FOUND);
	}
	
	
	@CrossOrigin
	@GetMapping("admin/shipper/shipperName/{shipperName}")
	public ResponseEntity<Shipper> getAShipperByShipperNameHandler(@PathVariable String shipperName) throws ShipperException {
		
		return new ResponseEntity<Shipper>(shipperService.getShipperByShipperName(shipperName),HttpStatus.FOUND);
	}
	
}
