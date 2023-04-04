package com.andersen_intensive.hotel.repository;

import java.time.LocalDateTime;

public interface ReservationRepository {

    void addReservation(Client client, Apartment apartment, LocalDateTime checkInDate, LocalDateTime checkOutDate);

    List<Reservation> getReservationsByClient();

    List<Reservation> getReservationsByApartment();

    void addServiceForReservation();

    void checkInApartmentForClient(Apartment apartment, Client client, LocalDateTime checkInDate);

    void checkOutOfApartmentForClient(Apartment apartment, Client client, LocalDateTime checkOutDate);
}
