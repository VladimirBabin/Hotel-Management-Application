package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Reservation;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository {

    Reservation addReservation(Reservation reservation);

    Reservation getReservationById(UUID id);

    List<Reservation> getAllReservationsList();

    Reservation updateReservation(Reservation reservation);

    void deleteReservation(int id);

}
