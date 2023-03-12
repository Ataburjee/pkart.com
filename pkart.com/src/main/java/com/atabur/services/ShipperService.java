package com.atabur.services;

import java.util.List;

import com.atabur.exceptions.CustomerException;
import com.atabur.exceptions.OrderException;
import com.atabur.exceptions.ShipperException;
import com.atabur.models.Shipper;
import com.atabur.payload.responses.ShipperResponse;

public interface ShipperService {
	
	Shipper addShipper(Shipper shippers) throws ShipperException;
	
	Shipper updateAShipper(Shipper shippers) throws ShipperException;
	
	Shipper deleteAShipper(Long shipperID) throws ShipperException;
	
	ShipperResponse getShipperDetailsByCustomer(Long customerID,Long shipperID) throws ShipperException, CustomerException, OrderException;
	
	List<Shipper> getAllShippers() throws ShipperException;
	
	Shipper getShipperByShipperName(String shipperName) throws ShipperException;

	Shipper getShipperByShipperID(Long id) throws ShipperException;
	
}
