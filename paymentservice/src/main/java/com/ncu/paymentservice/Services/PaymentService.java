package com.ncu.paymentservice.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ncu.paymentservice.Model.Payment;
import com.ncu.paymentservice.Payload.PaymentDTO;
import com.ncu.paymentservice.Repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Transactional
    public Payment addPayment(PaymentDTO payment) {
        Payment paymentBeforeSave = new Payment(payment.getPaymentMode(), payment.getBookingId(), payment.getUpiId(),
                payment.getCardNumber());
        return paymentRepository.save(paymentBeforeSave);
    }
    
    public Optional<Payment> getDetails(int transactionId){
    	return paymentRepository.findById(transactionId);
    	
    }
}
