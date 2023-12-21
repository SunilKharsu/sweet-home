package com.ncu.bookingservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ncu.bookingservice.Model.BookingInfoEntity;
import com.ncu.bookingservice.Payload.Request.BookRoomRequest;
import com.ncu.bookingservice.Payload.Request.PaymentRequest;
import com.ncu.bookingservice.Payload.Response.BookRoomResponse;
import com.ncu.bookingservice.Services.BookingService;
import com.ncu.bookingservice.exception.PaymentException;

@RestController
public class BookingController {
    @Value("${payment.url}")
    private String paymentUrl;

    @Autowired
    BookingService bookingService;

    @Autowired
    RestTemplate restTemplate;


    @PostMapping(path = "/booking")
    public ResponseEntity<?> bookRoom(@RequestBody BookRoomRequest bookRoomRequest) {
        BookingInfoEntity bookingAfterSave = bookingService.bookRoom(bookRoomRequest);
        return new ResponseEntity<BookingInfoEntity>(bookingAfterSave, HttpStatus.CREATED);
    }
    
    

    @PostMapping("/booking/{bookingId}/transaction")
    public ResponseEntity<?> transactionBooking(@RequestBody PaymentRequest paymentRequest,
            @PathVariable int bookingId) {
    	
    	if(!paymentRequest.getPaymentMode().equalsIgnoreCase("UPI")  && !paymentRequest.getPaymentMode().equalsIgnoreCase("CARD")) {
    		throw new PaymentException("Invalid mode of payment", 400);
    	}
    	
        paymentRequest.setBookingId(bookingId);
        ResponseEntity<Integer> response = restTemplate.postForEntity(paymentUrl, paymentRequest, Integer.class);
        if (response.getStatusCode() == HttpStatus.CREATED) {
        	BookingInfoEntity booking = bookingService.updateTransactionId(bookingId, response.getBody());
        	String message = "Booking confirmed for user with aadhaar number: /n"
        			+ booking.getAadharNumber()
        			+ " | "
        			+ "Here are the booking details: " + booking.toString();
        	
        	System.out.println(message);
        	
            return new ResponseEntity<BookingInfoEntity>(booking, HttpStatus.OK);
        } else {
            return new ResponseEntity("something went wrong", HttpStatus.BAD_GATEWAY);
        }
    }
}
