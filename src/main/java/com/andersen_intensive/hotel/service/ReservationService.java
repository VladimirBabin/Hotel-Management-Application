package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.*;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import com.andersen_intensive.hotel.repository.ClientRepository;
import com.andersen_intensive.hotel.repository.ReservationRepository;
import com.andersen_intensive.hotel.repository.UtilityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private ApartmentRepository apartmentRepository;
    private ClientRepository clientRepository;
    private UtilityRepository utilityRepository;

    public ReservationService(ReservationRepository reservationRepository
                                , ApartmentRepository apartmentRepository
                                , ClientRepository clientRepository
                                , UtilityRepository utilityRepository) {
        this.reservationRepository = reservationRepository;
        this.apartmentRepository = apartmentRepository;
        this.clientRepository = clientRepository;
        this.utilityRepository = utilityRepository;
    }

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

        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isEmpty()) {
            throw new EntityNotFoundException("Client with this id does not exist");
        }
        Client client = clientOptional.get();

        client.setReservation(reservation);
        apartment.setReservation(reservation);
        reservation.setApartment(apartmentOptional.get());
        reservation.setClient(client);

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

    @Transactional
    public Reservation createUtilityForReservation(Long reservationId, Long utilityId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isEmpty()) {
            throw new EntityNotFoundException("Reservation with this id does not exist");
        }
        Reservation reservation = reservationOptional.get();

        Optional<Utility> utilityOptional = utilityRepository.findById(utilityId);
        if (utilityOptional.isEmpty()) {
            throw new EntityNotFoundException("Utility with this id does not exist");
        }
        Utility utility = utilityOptional.get();

        List<Reservation> reservations = utility.getReservations();
        reservations.add(reservation);

        List<Utility> utilities = reservation.getUtilities();
        utilities.add(utility);

        utility.setReservations(reservations);
        reservation.setUtilities(utilities);
        reservationRepository.save(reservation);
        utilityRepository.save(utility);
        return reservation;
    }

    public BigDecimal getCurrentPrice(Long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isEmpty()) {
            throw new EntityNotFoundException("Reservation with this id does not exist");
        }
        BigDecimal result;
        Reservation reservation = reservationOptional.get();
        BigDecimal apartmentPrice = reservation.getApartment().getPrice();
        List<Utility> utilities = reservation.getUtilities();

        BigDecimal daysBetween = new BigDecimal(ChronoUnit.DAYS.between(reservation.getCheckIn(), reservation.getCheckOut()));
        result = apartmentPrice.multiply(daysBetween);

        for (Utility utility: utilities) {
            result = result.add(utility.getPrice());
        }

        return result;
    }
}
