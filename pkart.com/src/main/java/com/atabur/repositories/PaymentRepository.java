package com.atabur.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atabur.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

	List<Payment> findByPaymentType(String payType);

	List<Payment> findByStatus(Boolean status);

}
