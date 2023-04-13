package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation createReservation(Reservation reservation);

    Reservation getReservationByID(int id);

    List<Reservation> getReservationList();

    void removeReservation(int id);

    void updateReservation(Reservation reservationToUpdate);

    boolean checkIfReservationIsOpen(Reservation reservation);
}
