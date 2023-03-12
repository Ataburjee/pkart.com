package com.atabur.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atabur.exceptions.CustomerException;
import com.atabur.exceptions.OrderException;
import com.atabur.exceptions.PaymentsException;
import com.atabur.exceptions.ProductException;
import com.atabur.exceptions.ShipperException;
import com.atabur.models.Order;
import com.atabur.models.OrderDetail;
import com.atabur.repositories.CustomerRepository;
import com.atabur.repositories.OrderDetailRepo;
import com.atabur.repositories.OrderRepository;
import com.atabur.repositories.PaymentRepository;
import com.atabur.repositories.ProductRepo;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderDetailRepo detailRepo;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepo productRepository;
	
	@Autowired
	private ShipperRepository shipperRepository;

	@Autowired
	private PaymentRepository paymentRepository;
	
	
	@Override
	public Order placeOrder(Order order) throws CustomerException, ShipperException, ProductException, PaymentsException {
		
		if(customerRepository.findById(order.getCustomerid()).isEmpty()) throw new CustomerException("customer not exists...!");
		if(productRepository.findById(order.getProductid()).isEmpty()) throw new ProductException("product does not exists...!");
		if(shipperRepository.findById(order.getShipperid()).isEmpty()) throw new ShipperException("shipper does not exists...!");
		if(paymentRepository.findById(order.getPaymentid()).isEmpty()) throw new PaymentsException("payment does not exists...!");
		
		
		Random random = new Random();
	    int randomNumber = random.nextInt(900000000) + 100000000;
		order.setOrderDetailsid(randomNumber);
		Order order2 = orderRepository.save(order);
		OrderDetail detail = new OrderDetail();
		detail.setId(randomNumber);
		detail.setOrderid(order.getId());
		detail.setPaymentid(order2.getPaymentid());
		detail.setSupplierid(order2.getShipperid());
		detailRepo.save(detail);
		return order2;
	}
	


	@Override
	public List<Order> getAllOrders() throws OrderException {
		List<Order> orders = orderRepository.findAll();
		if(orders.isEmpty()) throw new OrderException("No orders found...!");
		return orders;
	}

	@Override
	public Order getOrderDetailsByOrderID(Long orderID) throws OrderException {
		return orderRepository.findById(orderID)
				.orElseThrow(() -> new OrderException("No orders found...!"));
	}

	@Override
	public List<Order> getAllOrdersOfACustomer(Long customerID) throws CustomerException, OrderException {
		
		if(customerRepository.findById(customerID).isEmpty()) 
				throw new CustomerException("No such customer found with the id "+customerID);
		List<Order> orders = orderRepository.findByCustomerid(customerID);
		if(orders.isEmpty()) throw new OrderException("No orders found...!");
		return orders;
		
	}

	@Override
	public List<Order> getAllMyOrders(Long customerID) throws OrderException, CustomerException {

		List<Order> orders = orderRepository.findByCustomerid(customerID);
		if(orders.isEmpty()) throw new OrderException("No orders found...!");
		return orders;
		
	}


	

}
