package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.repository.*;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservationServiceImplTest {

    private static ReservationService reservationService;
    private static ClientService clientService;
    private static ApartmentService apartmentService;

    @BeforeAll
    static void setUp() {
        ReservationRepository reservationRepository = new ReservationRepositoryImpl();
        reservationService = new ReservationServiceImpl(reservationRepository);
        ClientRepository clientRepository = new ClientRepositoryImpl();
        clientService = new ClientServiceImpl(clientRepository);
        ApartmentRepository apartmentRepository = new ApartmentRepositoryImpl();
        apartmentService = new ApartmentServiceImpl(apartmentRepository);

        Client client1 = clientService.createClient("Peter", "Parker", "88005553535");
        Apartment apartment1 = apartmentService.create(1, BigDecimal.valueOf(150), ApartmentType.DOUBLE);
        Reservation newReservation1 = new Reservation(client1, apartment1, LocalDate.now());
        reservationService.createReservation(newReservation1);

        Client client2 = clientService.createClient("Bruce", "Wayne", "+79654876422");
        Apartment apartment2 = apartmentService.create(2, BigDecimal.valueOf(250), ApartmentType.SINGLE);
        Reservation newReservation2 = new Reservation(client2, apartment2, LocalDate.parse("2018-12-27"));
        reservationService.createReservation(newReservation2);
    }

    @Order(1)
    @Test
    void createReservationTest() {
        Client client = clientService.createClient("Vlad", "Pirozkov", "+79213334455");
        Apartment apartment = apartmentService.create(3, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        Reservation newReservation = new Reservation(client, apartment, LocalDate.now());
        reservationService.createReservation(newReservation);
        assertEquals(3, reservationService.getReservationList().size());
    }

    @Order(2)
    @Test
    void getReservationListTest() {
        assertEquals(3, reservationService.getReservationList().size());
    }

    @Order(3)
    @Test
    void getReservationByID() {
        assertEquals("Bruce", reservationService.getReservationByID(2).getClient().getFirstName());
    }

    @Order(4)
    @Test
    void updateReservationTest() {
        Reservation reservation = reservationService.getReservationByID(1);
        reservation.setCheckIn(LocalDate.parse("2018-12-27"));
        reservationService.updateReservation(reservation);

        assertEquals(LocalDate.parse("2018-12-27"), reservationService.getReservationByID(1).getCheckIn());
    }

    @Order(5)
    @Test
    void checkIfReservationIsOpenTest() {
        Reservation reservation = reservationService.getReservationByID(3);
        assertTrue(reservationService.checkIfReservationIsOpen(reservation));
    }

    @Order(6)
    @Test
    void removeReservationTest() {
        reservationService.removeReservation(2);
        assertEquals(2, reservationService.getReservationList().size());
    }
}