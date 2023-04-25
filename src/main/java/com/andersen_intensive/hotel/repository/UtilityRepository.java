package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Utility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityRepository extends JpaRepository<Utility, Long> {
    Utility findByName(String name);
}
