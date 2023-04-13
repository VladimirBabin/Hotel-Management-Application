package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.repository.ReservationRepositoryImpl;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservationServiceImplTest {

    private final ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl();
    private final ReservationService reservationService = new ReservationServiceImpl(reservationRepository) ;

    @BeforeEach
    void setUp() {
        Client client1 = new Client("Vlad", "Pirozkov", "+79213334455");
        Apartment apartment1 = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        Reservation newReservation1 = new Reservation(1, client1, apartment1, LocalDate.now());
        reservationService.createReservation(newReservation1);

        Client client2 = new Client("Bruce", "Wayne", "+79219218765");
        Apartment apartment2 = new Apartment(2, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        Reservation newReservation2 = new Reservation(2, client2, apartment2, LocalDate.now());
        reservationService.createReservation(newReservation2);
    }

    @Test
    void createReservationTest() {
        Client client = new Client("Vlad", "Pirozkov", "+79213334455");
        Apartment apartment = new Apartment(3, BigDecimal.valueOf(100), ApartmentType.DOUBLE);
        Reservation newReservation = new Reservation(3, client, apartment, LocalDate.now());
        reservationService.createReservation(newReservation);
        assertEquals(3, reservationService.getReservationList().size());
    }

    @Test
    void getReservationListTest() {
        assertEquals(2, reservationService.getReservationList().size());
    }

    @Test
    void getReservationByID() {
        assertEquals("Bruce", reservationService.getReservationByID(2).getClient().getFirstName());
    }

    @Test
    void updateReservationTest() {
        Reservation reservation = reservationService.getReservationByID(1);
        reservation.setCheckIn(LocalDate.parse("2018-12-27"));
        reservationService.updateReservation(reservation);

        assertEquals(LocalDate.parse("2018-12-27"), reservationService.getReservationByID(1).getCheckIn());
    }

    @Test
    void checkIfReservationIsOpenTest() {
        Reservation reservation = reservationService.getReservationByID(2);
        assertTrue(reservationService.checkIfReservationIsOpen(reservation));
    }

    @Test
    void removeReservationTest() {
        reservationService.removeReservation(2);
        assertEquals(1, reservationService.getReservationList().size());
    }
}