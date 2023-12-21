package com.ncu.paymentservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ncu.paymentservice.Model.TransactionDetailsEntity;

public interface PaymentRepository extends JpaRepository<TransactionDetailsEntity, Integer> {

}
