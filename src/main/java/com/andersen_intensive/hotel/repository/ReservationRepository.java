package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository {

    Reservation addReservation(Reservation reservation);

    Reservation getReservationById(int id);

    Reservation updateReservation(Reservation reservation);

    void deleteReservation(Reservation reservation);

}
