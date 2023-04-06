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

    public void updateReservation(Reservation reservation) {
        reservationRepository.updateReservation(reservation);
    }

    public Reservation findByUserId(int id) {
        return reservationRepository.findByUserId(id);
    }
}
