package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;

    public Apartment saveApartment(int apartmentId, BigDecimal price, ApartmentType apartmentType, ApartmentStatus apartmentStatus) {
        Apartment apartmentFromMemory = apartmentRepository.findApartmentById(apartmentId);

        if (apartmentFromMemory != null) {
            throw new IllegalArgumentException("Apartment with this number is already exist");
        }

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cant be negative");
        }

        Apartment apartment = new Apartment(apartmentId, price, apartmentType, apartmentStatus);
        apartmentRepository.save(apartment);
        return apartment;
    }

    public List<Apartment> showAll() {
        return apartmentRepository.findAll();
    }

    public Apartment findById(int apartmentId) {
        Optional<Apartment> apartmentFromMemory = apartmentRepository.findById(apartmentId);
        if (apartmentFromMemory.isEmpty()) {
            throw new EntityNotFoundException("Entity with this id does not exist");
        }
        return apartmentFromMemory.get();
    }
}
