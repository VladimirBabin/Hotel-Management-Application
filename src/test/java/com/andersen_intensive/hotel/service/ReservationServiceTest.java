package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.*;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import com.andersen_intensive.hotel.repository.ClientRepository;
import com.andersen_intensive.hotel.repository.ReservationRepository;
import com.andersen_intensive.hotel.repository.UtilityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReservationServiceTest {

    private final ReservationRepository reservationRepository = mock(ReservationRepository.class);
    private final ApartmentRepository apartmentRepository = mock(ApartmentRepository.class);
    private final ClientRepository clientRepository = mock(ClientRepository.class);
    private final UtilityRepository utilityRepository = mock(UtilityRepository.class);

    private final ReservationService reservationService = new ReservationService(
            reservationRepository,
            apartmentRepository,
            clientRepository,
            utilityRepository
    );

    @Test
    public void testCreateReservation() {
        Reservation reservation = new Reservation(
                LocalDate.of(2023, 4, 25),
                LocalDate.of(2023, 4, 26)
        );
        Apartment apartment = new Apartment();
        apartment.setApartmentStatus(ApartmentStatus.AVAILABLE);
        Client client = new Client();
        when(apartmentRepository.findById(1L)).thenReturn(Optional.of(apartment));
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        assertEquals(reservation, reservationService.createReservation(reservation, 1L, 1L));
    }

    @Test
    public void testCreateReservationWhenCheckInIsAfterCheckOut() {
        Reservation reservation = new Reservation(
                LocalDate.of(2023, 4, 25),
                LocalDate.of(2023, 4, 24)
        );
        assertThrows(IllegalArgumentException.class,
                () -> reservationService.createReservation(reservation, 1L, 1L));
    }

    @Test
    public void testCreateReservationWhenCheckInEqualsCheckOut() {
        Reservation reservation = new Reservation(LocalDate.now(), LocalDate.now());
        assertThrows(IllegalArgumentException.class,
                () -> reservationService.createReservation(reservation, 1L, 1L));
    }

    @Test
    public void testCreateReservationWhenApartmentIsNotAvailable() {
        Reservation reservation = new Reservation(
                LocalDate.of(2023, 4, 25),
                LocalDate.of(2023, 4, 26)
        );
        Apartment apartment = new Apartment();
        apartment.setApartmentStatus(ApartmentStatus.UNAVAILABLE);
        when(apartmentRepository.findById(1L)).thenReturn(Optional.of(apartment));
        assertThrows(IllegalStateException.class,
                () -> reservationService.createReservation(reservation, 1L, 1L));
    }

    @Test
    public void testGetReservationById() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setCheckIn(LocalDate.now());
        reservation.setCheckOut(LocalDate.now().plusDays(1));
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        Reservation result = reservationService.getReservationByID(1L);
        assertEquals(reservation, result);
    }

    @Test
    public void testGetReservationByIdNotFound() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> reservationService.getReservationByID(1L));
    }

    @Test
    public void testGetAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        when(reservationRepository.findAll()).thenReturn(reservations);

        List<Reservation> result = reservationService.getAllReservations();
        assertEquals(reservations, result);
    }

    @Test
    public void testCreateUtilityForReservation() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        Utility utility = new Utility();
        utility.setId(2L);
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(utilityRepository.findById(2L)).thenReturn(Optional.of(utility));

        Reservation result = reservationService.createUtilityForReservation(1L, 2L);
        assertEquals(reservation, result);
        assertTrue(reservation.getUtilities().contains(utility));
        assertTrue(utility.getReservations().contains(reservation));
    }

    @Test
    public void testCreateUtilityForReservationReservationNotFound() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> reservationService.createUtilityForReservation(1L, 2L));
    }

    @Test
    public void testCreateUtilityForReservationUtilityNotFound() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(utilityRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> reservationService.createUtilityForReservation(1L, 2L));
    }

    @Test
    public void testGetCurrentPrice() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);

        Apartment apartment = new Apartment();
        BigDecimal apartmentPrice = BigDecimal.valueOf(100);
        apartment.setPrice(apartmentPrice);

        Utility utility1 = new Utility();
        BigDecimal utilityPrice1 = BigDecimal.valueOf(10);
        utility1.setPrice(utilityPrice1);

        Utility utility2 = new Utility();
        BigDecimal utilityPrice2 = BigDecimal.valueOf(20);
        utility2.setPrice(utilityPrice2);

        reservation.setApartment(apartment);
        reservation.setCheckIn(LocalDate.now());
        reservation.setCheckOut(LocalDate.now().plusDays(2));
        reservation.setUtilities(Arrays.asList(utility1, utility2));

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        BigDecimal expectedPrice = BigDecimal.valueOf(230); // 100 * 2 + 10 + 20

        assertEquals(expectedPrice, reservationService.getCurrentPrice(1L));
    }
}