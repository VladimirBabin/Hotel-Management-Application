package com.andersen_intensive.hotel.DEPRECATEDrepository;

import com.andersen_intensive.hotel.models.Reservation;
import java.util.List;

public interface ReservationRepository {

    Reservation addReservation(Reservation reservation);

    Reservation getReservationById(int id);

    List<Reservation> getAllReservationsList();

    Reservation updateReservation(Reservation reservation);

    void deleteReservation(int id);
}
