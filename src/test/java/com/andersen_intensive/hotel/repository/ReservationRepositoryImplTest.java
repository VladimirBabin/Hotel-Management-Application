package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.service.*;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservationRepositoryImplTest {

    private static ClientService clientService;
    private static ApartmentService apartmentService;
    private static ReservationRepository reservationRepository;

    @BeforeAll
    static void setUp() {
        reservationRepository = new ReservationRepositoryImpl();
        ClientRepository clientRepository = new ClientRepositoryImpl();
        clientService = new ClientServiceImpl(clientRepository);
        ApartmentRepository apartmentRepository = new ApartmentRepositoryImpl();
        apartmentService = new ApartmentServiceImpl(apartmentRepository);

        Client client1 = clientService.createClient("Peter", "Parker", "88005553535");
        Apartment apartment1 = apartmentService.create(1, BigDecimal.valueOf(150), ApartmentType.DOUBLE);
        Reservation newReservation1 = new Reservation(client1, apartment1, LocalDate.now());
        reservationRepository.addReservation(newReservation1);

        Client client2 = clientService.createClient("Bruce", "Wayne", "+79654876422");
        Apartment apartment2 = apartmentService.create(2, BigDecimal.valueOf(250), ApartmentType.SINGLE);
        Reservation newReservation2 = new Reservation(client2, apartment2, LocalDate.parse("2018-12-27"));
        reservationRepository.addReservation(newReservation2);
    }

    @Order(1)
    @Test
    void addReservation() {
        Client client = clientService.createClient("Vlad", "Pirozkov", "+79213334455");
        Apartment apartment = apartmentService.create(3, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        Reservation newReservation = new Reservation(client, apartment, LocalDate.now());

        reservationRepository.addReservation(newReservation);
        assertEquals(3, reservationRepository.getAllReservationsList().size());
    }

    @Order(2)
    @Test
    void getReservationById() {
        assertEquals("Vlad", reservationRepository.getReservationById(3).getClient().getFirstName());
    }

    @Order(3)
    @Test
    void getAllReservationsList() {
        assertEquals(3, reservationRepository.getAllReservationsList().size());
    }

    @Order(4)
    @Test
    void updateReservation() {
        Reservation reservation = reservationRepository.getReservationById(1);
        reservation.setCheckIn(LocalDate.parse("2018-12-27"));
        reservationRepository.updateReservation(reservation);
        assertEquals(LocalDate.parse("2018-12-27"), reservationRepository.getReservationById(1).getCheckIn());
    }

    @Order(5)
    @Test
    void deleteReservation() {
        reservationRepository.deleteReservation(2);
        assertEquals(2, reservationRepository.getAllReservationsList().size());
    }
}