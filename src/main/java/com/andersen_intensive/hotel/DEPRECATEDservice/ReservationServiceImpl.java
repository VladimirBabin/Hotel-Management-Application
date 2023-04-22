package com.andersen_intensive.hotel.DEPRECATEDservice;

import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.DEPRECATEDrepository.ReservationRepository;
import java.util.List;

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
    public Reservation getReservationByID(int id) {
        Reservation reservationFromMemory = reservationRepository.getReservationById(id);
        if (reservationFromMemory == null) {
            return null;
        }
        return reservationFromMemory;
    }

    @Override
    public List<Reservation> getReservationList() {
        return reservationRepository.getAllReservationsList();
    }

    @Override
    public void removeReservation(int id) {
        reservationRepository.deleteReservation(id);
    }

    @Override
    public Reservation updateReservation(Reservation reservationToUpdate) {
        return reservationRepository.updateReservation(reservationToUpdate);
    }

    public boolean checkIfReservationIsOpen(Reservation reservation) {
        if (reservation.getCheckOut() == null) {
            return true;
        }
        return false;
    }
}
