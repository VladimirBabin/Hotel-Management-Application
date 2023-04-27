package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.dto.ReservationDto;
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
                LocalDate.of(2023, 4, 25)
        );
        Apartment apartment = new Apartment();
        apartment.setApartmentStatus(ApartmentStatus.AVAILABLE);
        Client client = new Client();

        ReservationDto reservationDto = new ReservationDto(1L, 1L, 1L,
                LocalDate.of(2023, 4, 25),  0L);

        when(apartmentRepository.findById(1L)).thenReturn(Optional.of(apartment));
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        assertEquals(reservation.getId(), reservationService.createReservation(reservationDto).getId());
    }


    @Test
    public void testCreateReservationWhenApartmentIsNotAvailable() {
        Apartment apartment = new Apartment();
        apartment.setApartmentStatus(ApartmentStatus.UNAVAILABLE);
        ReservationDto reservationDto = new ReservationDto(1L, 1L, 1L,
                LocalDate.of(2023, 4, 25),  0L);

        when(apartmentRepository.findById(1L)).thenReturn(Optional.of(apartment));
        assertThrows(IllegalStateException.class,
                () -> reservationService.createReservation(reservationDto));
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
    public void testAddUtilityForReservation() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        Utility utility = new Utility();
        utility.setId(2L);
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(utilityRepository.findById(2L)).thenReturn(Optional.of(utility));

        ReservationDto reservationDto = new ReservationDto(1L, 1L, 1L,
                LocalDate.of(2023, 4, 25),  2L);

        Reservation result = reservationService.addUtilityForReservation(reservationDto);
        assertEquals(reservation, result);
        assertTrue(reservation.getUtilities().contains(utility));
    }

    @Test
    public void testAddUtilityForReservationReservationNotFound() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());
        ReservationDto reservationDto = new ReservationDto(1L, 1L, 1L,
                LocalDate.of(2023, 4, 25),  0L);

        assertThrows(EntityNotFoundException.class,
                () -> reservationService.addUtilityForReservation(reservationDto));
    }

    @Test
    public void testAddUtilityForReservationUtilityNotFound() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(utilityRepository.findById(2L)).thenReturn(Optional.empty());

        ReservationDto reservationDto = new ReservationDto(1L, 1L, 1L,
                LocalDate.of(2023, 4, 25),  2L);

        assertThrows(EntityNotFoundException.class,
                () -> reservationService.addUtilityForReservation(reservationDto));
    }

    @Test
    public void testCheckOutAndGetCurrentPrice() {
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
        reservation.setCheckIn(LocalDate.now().minusDays(2));
        reservation.setCheckOut(LocalDate.now());
        reservation.setUtilities(Arrays.asList(utility1, utility2));

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        BigDecimal expectedPrice = BigDecimal.valueOf(230); // 100 * 2 + 10 + 20

        assertEquals(expectedPrice, reservationService.getCurrentPrice(1L));
    }
}