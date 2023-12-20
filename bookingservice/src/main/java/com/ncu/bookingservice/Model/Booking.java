package com.ncu.bookingservice.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer bookingId;
    @Column
    Date fromDate;
    @Column
    Date toDate;
    @Column
    String aadharNumber;
    @Column
    Integer numOfRooms;
    @Column
    String roomNumbers;
    @Column(nullable = false)
    Integer roomPrice;
    @Column
    Integer transactionId = 0;
    

    public Booking(Date fromDate, Date toDate, String aadharNumber, Integer numOfRooms, String roomNumbers,
            Integer roomPrice) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.aadharNumber = aadharNumber;
        this.numOfRooms = numOfRooms;
        this.roomNumbers = roomNumbers;
        this.roomPrice = roomPrice;
        
    }

    public Booking(Date fromDate, Date toDate, String aadharNumber, Integer numOfRooms, String roomNumbers,
            Integer roomPrice, Integer transactionId) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.aadharNumber = aadharNumber;
        this.numOfRooms = numOfRooms;
        this.roomNumbers = roomNumbers;
        this.roomPrice = roomPrice;
        this.transactionId = transactionId;
        
    }
}
