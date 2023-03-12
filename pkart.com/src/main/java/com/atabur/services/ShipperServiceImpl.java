package com.atabur.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atabur.exceptions.CustomerException;
import com.atabur.exceptions.OrderException;
import com.atabur.exceptions.ShipperException;
import com.atabur.models.Order;
import com.atabur.models.Shipper;
import com.atabur.payload.responses.ShipperResponse;
import com.atabur.repositories.OrderRepository;

@Service
public class ShipperServiceImpl implements ShipperService{
	
	@Autowired
	private ShipperRepository shipperRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Shipper> getAllShippers() throws ShipperException {
		List<Shipper> shippers =  shipperRepository.findAll();
		if(shippers!=null) return shippers;
		else throw new ShipperException("No shippers available...!");
	}

	@Override
	public Shipper getShipperByShipperName(String shipperName) throws ShipperException {
		Shipper shippers =  shipperRepository.findByCompanyName(shipperName);
		if(shippers!=null) return shippers;
		else throw new ShipperException("Shippers not available with the name "+shipperName);
	}

	@Override
	public Shipper getShipperByShipperID(Long id) throws ShipperException {
		return shipperRepository.findById(id)
				.orElseThrow(() -> new ShipperException("Shippers not available with the id "+id));
	}

	@Override
	public Shipper addShipper(Shipper shippers) throws ShipperException {
		if(shipperRepository.existsByCompanyName(shippers.getCompanyName())) throw new ShipperException("Company already there...!");
		if(shipperRepository.existsByMobile(shippers.getMobile())) throw new ShipperException("Company mobile no already there...!");
		if(shipperRepository.existsByEmail(shippers.getEmail())) throw new ShipperException("Company email already there...!");
		return shipperRepository.save(shippers);
	}

	@Override
	public Shipper updateAShipper(Shipper shippers) throws ShipperException {
		
		if(!shipperRepository.existsById(shippers.getId())) throw new ShipperException("Shippers not available with the id "+shippers.getId());
		shipperRepository.deleteById(shippers.getId());
		if(shipperRepository.existsByCompanyName(shippers.getCompanyName())) throw new ShipperException("Company already there...!");
		if(shipperRepository.existsByMobile(shippers.getMobile())) throw new ShipperException("Company mobile no already there...!");
		if(shipperRepository.existsByEmail(shippers.getEmail())) throw new ShipperException("Company email already there...!");
		return shipperRepository.save(shippers);
		
	}

	@Override
	public Shipper deleteAShipper(Long shipperID) throws ShipperException {
		if(!shipperRepository.existsById(shipperID)) throw new ShipperException("Shippers not available with the id '"+shipperID+"'");
		Optional<Shipper> shOptional = shipperRepository.findById(shipperID);
		shipperRepository.deleteById(shipperID);
		return shOptional.get();
	}

	@Override
	public ShipperResponse getShipperDetailsByCustomer(Long customerID, Long shipperID)
			throws ShipperException, CustomerException, OrderException {
		if(!shipperRepository.existsById(shipperID)) throw new ShipperException("Shippers not available with the id '"+shipperID+"'");
		Optional<Order> optional = orderRepository.findById(shipperID);
		if(!optional.get().getCustomer().getId().equals(customerID)) throw new CustomerException("Customer order is not assotiated with the shipper");
		Optional<Shipper> optional2 = shipperRepository.findById(shipperID);
		return new ShipperResponse(optional2.get().getCompanyName(), optional2.get().getMobile(), optional2.get().getEmail());
		
	}

}
