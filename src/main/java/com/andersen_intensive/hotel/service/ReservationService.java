package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReservationService {

    Reservation createReservation(Reservation reservation);

    //read
    Reservation getReservationByID(UUID id);

    List<Reservation> getReservationList(boolean sortByID);

    //delete
    void removeReservation(int id);

    //update
    void updateReservation(Reservation reservationToUpdate);

    boolean checkIfReservationIsOpen(Reservation reservation);
}
