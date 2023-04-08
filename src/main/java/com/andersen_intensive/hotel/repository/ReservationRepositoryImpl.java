package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Reservation;

import java.util.*;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final Map<Integer, Reservation> reservations = new HashMap<>();

    @Override
    public Reservation addReservation(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    @Override
    public Reservation getReservationById(UUID id) {
        return reservations.get(id);
    }

    @Override
    public List<Reservation> getAllReservationsList() {
        return new ArrayList<>(reservations.values());
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    @Override
    public void deleteReservation(int id) {
        reservations.remove(id);
    }

    public Reservation findByUserId(UUID userId) {
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
