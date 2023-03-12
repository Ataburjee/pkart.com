package com.atabur.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atabur.exceptions.CustomerException;
import com.atabur.exceptions.OrderException;
import com.atabur.exceptions.PaymentsException;
import com.atabur.models.Payment;
import com.atabur.services.PaymentService;

@RestController
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/admin/payment/all")
	ResponseEntity<List<Payment>> getAllPaymentHandler() throws PaymentsException{
		return new ResponseEntity<List<Payment>>(paymentService.getAllPayments(),HttpStatus.FOUND);
	}
	
	@GetMapping("/admin/payment/{id}")
	ResponseEntity<Payment> getPaymentByIdHandler(@PathVariable Long id) throws PaymentsException{
		return new ResponseEntity<Payment>(paymentService.getPaymentsDetailsByID(id),HttpStatus.FOUND);
	}
	
	@GetMapping("/admin/payment/status/{status}")
	ResponseEntity<List<Payment>> getAllPaymentByStatusHandler(@PathVariable Boolean status) throws PaymentsException{
		return new ResponseEntity<List<Payment>>(paymentService.getAllPaymentsByStatus(status), HttpStatus.FOUND);
	}
	
	@GetMapping("/admin/payment/type/{type}")
	ResponseEntity<List<Payment>> getAllPaymentByTypeHandler(@PathVariable String type) throws PaymentsException{
		return new ResponseEntity<List<Payment>>(paymentService.getAllPaymentsByPaymentType(type), HttpStatus.FOUND);
	}
	
	@GetMapping("/customer/payment/{customerID}/{paymentID}")
	ResponseEntity<Payment> getPaymentByIdHandler(@PathVariable Long customerID, @PathVariable Long paymentID) throws PaymentsException, CustomerException, OrderException{
		return new ResponseEntity<Payment>(paymentService.getPaymentsDetailsByIDByCustomer(customerID, paymentID),HttpStatus.FOUND);
	}
	
	@PostMapping("/user/payment")
	ResponseEntity<Payment> UserPaymentHandler(@Valid @RequestBody Payment payment) throws PaymentsException {
		return new ResponseEntity<Payment>(paymentService.payment(payment),HttpStatus.ACCEPTED);
	}
	
}
