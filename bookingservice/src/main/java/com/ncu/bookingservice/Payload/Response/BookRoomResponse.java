package com.ncu.bookingservice.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRoomResponse {
    String roomNumbers;
    Integer priceForAllRooms;
}
