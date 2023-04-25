package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findById(Long id);
}
