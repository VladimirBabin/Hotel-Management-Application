package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.repository.*;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation createReservation(Reservation newReservation) {
        return reservationRepository.addReservation(newReservation);
    }

    @Override
    public Reservation getReservationByID(UUID id) {
        return reservationRepository.getReservationById(id);
    }

    @Override
    public List<Reservation> getReservationList(boolean sortByID) {
        List<Reservation> reservations = reservationRepository.getAllReservationsList();
        if (sortByID) {
            reservations.sort(Comparator.comparing(Reservation::getId));
        }
        return reservations;
    }

    @Override
    public void removeReservation(int id) {
        reservationRepository.deleteReservation(id);
    }

    @Override
    public void updateReservation(Reservation reservationToUpdate) {
        reservationRepository.updateReservation(reservationToUpdate);
    }

    public boolean checkIfReservationIsOpen(Reservation reservation) {
        if (reservation.getCheckOut() == null) {
            return true;
        }
        return false;
    }


    /*public void addReservation(Reservation reservation) {
        reservationRepository.addReservation(reservation);
    }

    public void updateReservation(Reservation reservation) {
        reservationRepository.updateReservation(reservation);
    }

    public Reservation findByUserId(int id) {
        return reservationRepository.findByUserId(id);
    }

    public boolean checkIfReservationIsOpen(Reservation reservation) {
        if (reservation.getCheckOut() == null) {
            return true;
        }
        return false;
    }*/
//
//    public boolean checkIfOpenReservationExistsForClient(int userId) {
//        return reservationRepository.checkIfOpenReservationExistsForClient(userId);
//    }

}
