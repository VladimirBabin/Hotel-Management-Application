package com.andersen_intensive.hotel.repository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository {

    void addReservation(Reservation reservation);

    List<Reservation> getReservationsByClient();

    List<Reservation> getReservationsByApartment();

    void addServiceForReservation();

    void checkInApartmentForClient(Apartment apartment, Client client, LocalDateTime checkInDate);

    void checkOutOfApartmentForClient(Apartment apartment, Client client, LocalDateTime checkOutDate);
}
