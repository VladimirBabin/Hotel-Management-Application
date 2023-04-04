package com.andersen_intensive.hotel.repository;

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

    @Override
    public void checkInApartmentForClient(Apartment apartment, Client client, LocalDateTime checkInDate) {

    }

    @Override
    public void checkOutOfApartmentForClient(Apartment apartment, Client client, LocalDateTime checkOutDate) {

    }
}
