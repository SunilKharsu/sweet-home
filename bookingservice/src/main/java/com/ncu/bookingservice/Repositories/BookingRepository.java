package com.ncu.bookingservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncu.bookingservice.Model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    public Booking findByTransactionId(Integer transactionId);
}
