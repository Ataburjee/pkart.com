package com.atabur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atabur.models.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long>{

}
