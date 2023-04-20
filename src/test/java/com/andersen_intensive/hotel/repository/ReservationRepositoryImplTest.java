package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.models.Reservation;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationRepositoryImplTest {

    private final ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl(new UtilityRepositoryImpl());

    @BeforeEach
    void setUp() {
        Client client1 = new Client("Vlad", "Pirozkov", "+79213334455", 111);
        Apartment apartment1 = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        Reservation newReservation1 = new Reservation(1,
                client1.getPersonalID(),
                apartment1.getApartmentId(),
                LocalDate.now());
        reservationRepository.addReservation(newReservation1);

        Client client2 = new Client("Bruce", "Wayne", "+79219218765", 222);
        Apartment apartment2 = new Apartment(2, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        Reservation newReservation2 = new Reservation(2,
                client2.getPersonalID(),
                apartment2.getApartmentId(),
                LocalDate.now());
        reservationRepository.addReservation(newReservation2);

        Client client3 = new Client("Peter", "Parker", "+78128127654", 333);
        Apartment apartment3 = new Apartment(3, BigDecimal.valueOf(100), ApartmentType.DOUBLE);
        Reservation newReservation3 = new Reservation(3,
                client3.getPersonalID(),
                apartment3.getApartmentId(),
                LocalDate.now());
        reservationRepository.addReservation(newReservation3);
    }

    @Test
    void addReservation() {
        Client client = new Client("Vlad", "Pirozkov", "+79213334455", 444);
        Apartment apartment = new Apartment(4, BigDecimal.valueOf(100), ApartmentType.DOUBLE);
        Reservation newReservation = new Reservation(4,
                client.getPersonalID(),
                apartment.getApartmentId(),
                LocalDate.now());

        reservationRepository.addReservation(newReservation);
        assertEquals(4, reservationRepository.getAllReservationsList().size());
    }

    @Test
    void getReservationById() {
        assertEquals(333, reservationRepository.getReservationById(3).getClientID());
    }

    @Test
    void getAllReservationsList() {
        assertEquals(3, reservationRepository.getAllReservationsList().size());
    }

    @Test
    void updateReservation() {
        Reservation reservation = reservationRepository.getReservationById(1);
        reservation.setCheckIn(LocalDate.parse("2018-12-27"));
        reservationRepository.updateReservation(reservation);
        assertEquals(LocalDate.parse("2018-12-27"), reservationRepository.getReservationById(1).getCheckIn());
    }

    @Test
    void deleteReservation() {
        reservationRepository.deleteReservation(2);
        assertEquals(2, reservationRepository.getAllReservationsList().size());
    }
}