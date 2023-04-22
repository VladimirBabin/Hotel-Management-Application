package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public Apartment saveApartment(Apartment apartment) {
        if (apartment.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cant be negative");
        }

        apartmentRepository.save(apartment);
        return apartment;
    }

    public List<Apartment> showAll() {
        return apartmentRepository.findAll();
    }

    public Apartment findById(Long id) {
        Optional<Apartment> apartmentFromMemory = apartmentRepository.findById(id);
        if (apartmentFromMemory.isEmpty()) {
            throw new EntityNotFoundException("Entity with this id does not exist");
        }
        return apartmentFromMemory.get();
    }
}
