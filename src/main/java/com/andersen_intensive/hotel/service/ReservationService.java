package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import com.andersen_intensive.hotel.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ApartmentRepository apartmentRepository;

    public Reservation createReservation(Reservation reservation, Long apartmentId, Long clientId) {
        if (reservation.getCheckIn().equals(reservation.getCheckOut())) {
            throw new IllegalArgumentException("Check in and check out cannot be equal");
        }

        if (reservation.getCheckIn().isAfter(reservation.getCheckOut())) {
            throw new IllegalArgumentException("Check in cannot be after check out date");
        }

        Optional<Apartment> apartmentOptional = apartmentRepository.findById(apartmentId);
        if (apartmentOptional.isEmpty()) {
            throw new EntityNotFoundException("Apartment with this id does not exist");
        }

        Apartment apartment = apartmentOptional.get();
        if (apartment.getApartmentStatus() != ApartmentStatus.AVAILABLE){
            throw new IllegalStateException("Apartment is not available for reservation");
        }

        apartment.setReservation(reservation);
        reservation.setApartment(apartmentOptional.get());

        reservationRepository.save(reservation);
        return reservation;
    }

    public Reservation getReservationByID(Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isEmpty()) {
            throw new EntityNotFoundException("Reservation with this id does not exist");
        }
        return reservationOptional.get();
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
