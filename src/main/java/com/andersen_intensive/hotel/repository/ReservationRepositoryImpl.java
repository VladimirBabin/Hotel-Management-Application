package com.andersen_intensive.hotel.repository;

import java.time.LocalDateTime;

public class ReservationRepositoryImpl implements ReservationRepository {
    @Override
    public void addReservation(Client client, Apartment apartment, LocalDateTime checkInDate, LocalDateTime checkOutDate) {

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
