package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.models.Reservation;

import java.util.HashMap;
import java.util.Map;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final Map<Integer, Reservation> reservations = new HashMap<>();

    private static ReservationRepositoryImpl INSTANCE;

    public static ReservationRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReservationRepositoryImpl();
        }
        return INSTANCE;
    }


    @Override
    public Reservation addReservation(Reservation reservation) {
        return null;
    }

    @Override
    public Reservation getReservationById(int id) {
        return null;
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return null;
    }

    @Override
    public void deleteReservation(Reservation reservation) {

    }

    public Reservation findByUserId(int id) {
        return null;
    }
}
