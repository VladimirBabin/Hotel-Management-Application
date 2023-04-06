package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.repository.*;

public class ReservationServiceImpl {

    private final ReservationRepositoryImpl reservationRepository = ReservationRepositoryImpl.getInstance();

    private static ReservationServiceImpl INSTANCE;

    public static ReservationServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReservationServiceImpl();
        }
        return INSTANCE;
    }


    public void addReservation(Reservation reservation) {
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
    }
//
//    public boolean checkIfOpenReservationExistsForClient(int userId) {
//        return reservationRepository.checkIfOpenReservationExistsForClient(userId);
//    }

}
