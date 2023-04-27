package com.andersen_intensive.hotel.dto;

import java.time.LocalDate;

public record ReservationDto(long id,
                             long clientId,
                             long apartmentId,
                             LocalDate checkIn,
                             LocalDate checkOut,
                             long utilityId) {
}
