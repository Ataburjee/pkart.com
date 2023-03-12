package com.atabur.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atabur.exceptions.CustomerException;
import com.atabur.exceptions.OrderException;
import com.atabur.exceptions.PaymentsException;
import com.atabur.models.Order;
import com.atabur.models.Payment;
import com.atabur.repositories.OrderRepository;
import com.atabur.repositories.PaymentRepository;

@Service
public class PaymentsServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository payRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Payment> getAllPayments() throws PaymentsException {
		List<Payment> payments = payRepository.findAll();
		if(payments.isEmpty()) throw new PaymentsException("No such payments found...!");
		return payments;
	}

	@Override
	public Payment getPaymentsDetailsByID(Long paymentID) throws PaymentsException {
		return payRepository.findById(paymentID)
				.orElseThrow(() -> 
				new PaymentsException("No such payments found with the ID "+paymentID));
	}

	@Override
	public List<Payment> getAllPaymentsByStatus(Boolean status) throws PaymentsException {
		
		List<Payment> payments = payRepository.findByStatus(status);
		if(payments.isEmpty()) throw new PaymentsException("No such payments found with the status "+status);
		return payments;
		
	}

	@Override
	public List<Payment> getAllPaymentsByPaymentType(String payType) throws PaymentsException {
		
		List<Payment> payments = payRepository.findByPaymentType(payType);
		if(payments.isEmpty()) throw new PaymentsException("No such payments found with the payment type "+payType);
		return payments;
		
	}

	@Override
	public Payment getPaymentsDetailsByIDByCustomer(Long customerID, Long paymentID)
			throws PaymentsException, CustomerException, OrderException {
		
		Optional<Payment> paOptional = payRepository.findById(paymentID);
		if(paOptional.isEmpty()) throw new PaymentsException("No such payments found with the ID "+paymentID);
		Optional<Order> optional = orderRepository.findById(paymentID);
		if(!optional.get().getPayment().getId().equals(paymentID)) throw new CustomerException("PaymentID is not assotiated with the customer");
		return paOptional.get();
	}

	@Override
	public Payment payment(@Valid Payment payment) {
		return payRepository.save(payment);
	}
	

}
