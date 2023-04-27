package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.dto.ReservationDto;
import com.andersen_intensive.hotel.models.*;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import com.andersen_intensive.hotel.repository.ClientRepository;
import com.andersen_intensive.hotel.repository.ReservationRepository;
import com.andersen_intensive.hotel.repository.UtilityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ApartmentRepository apartmentRepository;
    private final ClientRepository clientRepository;
    private final UtilityRepository utilityRepository;

    @Transactional
    public Reservation createReservation(ReservationDto reservationDto) {
        Optional<Apartment> apartmentOptional = apartmentRepository.findById(reservationDto.apartmentId());
        if (apartmentOptional.isEmpty()) {
            throw new EntityNotFoundException("Apartment with this id does not exist");
        }
        Apartment apartment = apartmentOptional.get();
        if (apartment.getApartmentStatus() != ApartmentStatus.AVAILABLE){
            throw new IllegalStateException("Apartment is not available for reservation");
        }

        Optional<Client> clientOptional = clientRepository.findById(reservationDto.clientId());
        if (clientOptional.isEmpty()) {
            throw new EntityNotFoundException("Client with this id does not exist");
        }
        Client client = clientOptional.get();

        LocalDate checkIn = reservationDto.checkIn();
        if (checkIn == null) {
            throw new IllegalArgumentException("Check in data should be present");
        }

        Reservation reservation = new Reservation(checkIn);
        reservation.setApartment(apartment);
        reservation.setClient(client);

        client.setReservation(reservation);
        apartment.setReservation(reservation);
        apartment.setApartmentStatus(ApartmentStatus.OCCUPIED);

        apartmentRepository.save(apartment);
        clientRepository.save(client);
        reservationRepository.save(reservation);

        return reservation;
    }

    public Reservation getReservationByID(long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isEmpty()) {
            throw new EntityNotFoundException("Reservation with this id does not exist");
        }
        return reservationOptional.get();
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional
    public Reservation addUtilityForReservation(ReservationDto reservationDto) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationDto.id());
        if (reservationOptional.isEmpty()) {
            throw new EntityNotFoundException("Reservation with this id does not exist");
        }
        Reservation reservation = reservationOptional.get();

        Optional<Utility> utilityOptional = utilityRepository.findById(reservationDto.utilityId());
        if (utilityOptional.isEmpty()) {
            throw new EntityNotFoundException("Utility with this id does not exist");
        }
        Utility utility = utilityOptional.get();

        List<Utility> utilities = reservation.getUtilities();
        if (utilities == null) {
            utilities = new ArrayList<>();
        }
        utilities.add(utility);
        reservation.setUtilities(utilities);
        reservationRepository.save(reservation);
        return reservation;
    }


    public BigDecimal getCurrentPrice(long reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isEmpty()) {
            throw new EntityNotFoundException("Reservation with this id does not exist");
        }
        Reservation reservation = reservationOptional.get();
        reservation.setCheckOut(LocalDate.now());

        BigDecimal result;
        BigDecimal apartmentPrice = reservation.getApartment().getPrice();
        List<Utility> utilities = reservation.getUtilities();

        BigDecimal daysBetween = new BigDecimal(ChronoUnit.DAYS.between(reservation.getCheckIn(),
                                                reservation.getCheckOut()));
        result = apartmentPrice.multiply(daysBetween);

        for (Utility utility: utilities) {
            result = result.add(utility.getPrice());
        }

        Apartment apartment = reservation.getApartment();
        apartment.setApartmentStatus(ApartmentStatus.AVAILABLE);
        apartmentRepository.save(apartment);
        reservationRepository.save(reservation);
        return result;
    }
}
