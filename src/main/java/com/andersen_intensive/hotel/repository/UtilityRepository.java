package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Utility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilityRepository extends JpaRepository<Utility, Integer> {
    Utility findByName(String name);
    Optional<Utility> findById(Long id);
}
