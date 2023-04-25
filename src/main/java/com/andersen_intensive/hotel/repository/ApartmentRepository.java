package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {
    Optional<Apartment> findById(Long id);
}
