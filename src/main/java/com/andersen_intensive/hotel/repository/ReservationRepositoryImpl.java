package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class ReservationRepositoryImpl implements ReservationRepository {

    List<Reservation> reservations;

    @Override
    public void addReservation(Reservation reservation) {
        if (reservations == null) {
            reservations = new ArrayList<Reservation>();
        }
        reservations.add(reservation);
    }

    @Override
    public List<Reservation> getReservationsByClient() {
        return null;
    }

    @Override
    public List<Reservation> getReservationsByApartment() {
        return null;
    }

    @Override
    public void addServiceForReservation() {

    }
}
