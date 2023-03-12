package com.atabur.services;

import java.util.List;

import javax.validation.Valid;

import com.atabur.exceptions.CustomerException;
import com.atabur.exceptions.OrderException;
import com.atabur.exceptions.PaymentsException;
import com.atabur.models.Payment;

public interface PaymentService {
	
	List<Payment> getAllPayments() throws PaymentsException;
	
	Payment getPaymentsDetailsByID(Long paymentID) throws PaymentsException;
	
	List<Payment> getAllPaymentsByStatus(Boolean status) throws PaymentsException;
	
	List<Payment> getAllPaymentsByPaymentType(String payType) throws PaymentsException;
	
	Payment getPaymentsDetailsByIDByCustomer(Long customerID,Long paymentID) throws PaymentsException, CustomerException, OrderException;

	Payment payment(@Valid Payment payment);
	
}
