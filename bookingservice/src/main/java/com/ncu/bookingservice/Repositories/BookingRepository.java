package com.ncu.bookingservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncu.bookingservice.Model.BookingInfoEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingInfoEntity, Integer> {
    public BookingInfoEntity findByTransactionId(Integer transactionId);
}
