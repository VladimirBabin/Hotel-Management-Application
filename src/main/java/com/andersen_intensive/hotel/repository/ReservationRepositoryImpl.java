package com.andersen_intensive.hotel.repository;

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
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    @Override
    public Reservation getReservationById(int id) {
        return reservations.get(id);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        reservations.remove(reservation.getId());
    }

    public Reservation findByUserId(int userId) {
        for(Reservation reservation: reservations.values()) {
            if (reservation.getClient().getPersonalID() == userId) {
                return reservation;
            }
        }
        return null;
    }

//    public boolean checkIfOpenReservationExistsForClient(int userId) {
//        for(Reservation reservation: reservations.values()) {
//            if ((reservation.getClient().getPersonalID() == userId)
//                    && reservation.getCheckOut() == null) {
//                return true;
//            }
//        }
//        return false;
//    }
}
