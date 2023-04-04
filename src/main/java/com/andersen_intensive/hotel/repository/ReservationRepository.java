package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository {

    void addReservation(Reservation reservation);

    List<Reservation> getReservationsByClient();

    List<Reservation> getReservationsByApartment();

    void addServiceForReservation();
}
