package com.ncu.bookingservice.Services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncu.bookingservice.Model.Booking;
import com.ncu.bookingservice.Payload.Request.BookRoomRequest;
import com.ncu.bookingservice.Payload.Response.BookRoomResponse;
import com.ncu.bookingservice.Repositories.BookingRepository;
import com.ncu.bookingservice.exception.PaymentException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public BookRoomResponse bookRoom(BookRoomRequest bookRoomRequest) {
        String roomNumbers = generateRoomNumbers(bookRoomRequest.getNumberOfRooms());
        Integer priceOfAllRooms = calculateRoomsPrice(bookRoomRequest.getNumberOfRooms(), bookRoomRequest.getFromDate(),
                bookRoomRequest.getToDate());
        Date currentDate = getCurrentDate();
        Booking booking = new Booking(bookRoomRequest.getFromDate(), bookRoomRequest.getToDate(),
                bookRoomRequest.getAadharNumber(), bookRoomRequest.getNumberOfRooms(), roomNumbers, priceOfAllRooms);
        Booking newBooking = bookingRepository.save(booking);
        
        System.out.println(newBooking.toString()); 

        
        return new BookRoomResponse(roomNumbers, priceOfAllRooms);
    }

    public Date getCurrentDate() {
        return Calendar.getInstance().getTime();
    }

    public Integer calculateRoomsPrice(Integer numberOfRooms, Date fromDate, Date toDate) {
        long diff = toDate.getTime() - fromDate.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        long roomPrice = 1000 * numberOfRooms * (diffDays);
        return (int) roomPrice;
    }

    public String generateRoomNumbers(Integer numberOfRooms) {

    	
    	Random randomNumber = new Random();
    	
    	StringBuilder sb = new StringBuilder();

    	for(int i=0;i<numberOfRooms;i++){
    	sb.append(" "+(randomNumber.nextInt(100)+1));

    	}

    	return sb.toString();
    }

    public Booking updateTransactionId(int bookingId, int transactionId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if(booking == null) {
        	throw new PaymentException("Invalid booking Id", 400);
        }
       
        else {
        	booking.setTransactionId(transactionId);
            return bookingRepository.save(booking);
        }
    }
}
