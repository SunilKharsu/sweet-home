package com.ncu.paymentservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ncu.paymentservice.Model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
