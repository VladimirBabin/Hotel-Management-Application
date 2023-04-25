package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findById(Long id);

    Client findByPhoneNumber(String phoneNumber);
}
