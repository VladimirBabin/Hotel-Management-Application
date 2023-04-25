package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.HotelManagementApplication;
import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import com.moandjiezana.toml.Toml;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApartmentService {
    private ApartmentRepository apartmentRepository;
    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

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

    public Apartment changePrice(Long id, BigDecimal newPrice) {
        Optional<Apartment> apartmentFromMemory = apartmentRepository.findById(id);

        if (apartmentFromMemory.isEmpty()) {
            throw new EntityNotFoundException("Apartment with this id does not exist");
        }

        if (newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cant be negative");
        }
        Apartment apartment = apartmentFromMemory.get();
        apartment.setPrice(newPrice);
        apartmentRepository.save(apartment);
        return apartment;
    }

    public Apartment updateStatus(long apartmentId, ApartmentStatus status) {
        Toml toml = new Toml().read(HotelManagementApplication.class.getResourceAsStream("/application.toml"));
        boolean apartmentStatusEnabled = toml.getBoolean("application.apartmentStatusChange.apartmentStatusEnabled");
        if (!apartmentStatusEnabled) {
            throw new IllegalStateException("Changing apartment status is not allowed.");
        }
        Optional<Apartment> apartmentFromMemory = apartmentRepository.findById(apartmentId);
        if (apartmentFromMemory.isEmpty()) {
            throw new EntityNotFoundException("Apartment with this id does not exist");
        }
        Apartment apartment = apartmentFromMemory.get();
        apartment.setApartmentStatus(status);
        apartmentRepository.save(apartment);
        return apartment;
    }

    public List<Apartment> sortByStatus() {
        List<Apartment> sortedApartments = apartmentRepository.findAll();
        sortedApartments.sort(Comparator.comparing(Apartment::getApartmentStatus));
        return sortedApartments;
    }
}
