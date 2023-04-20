package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import java.math.BigDecimal;
import java.util.List;

public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public Apartment create(int apartmentId, BigDecimal price, ApartmentType apartmentType) {
        Apartment apartment = new Apartment(apartmentId, price, apartmentType);
        return apartmentRepository.add(apartment);
    }
    public Apartment create(int apartmentId, BigDecimal price, ApartmentType apartmentType, ApartmentStatus apartmentStatus) {
        Apartment apartment = new Apartment(apartmentId, price, apartmentType, apartmentStatus);
        return apartmentRepository.add(apartment);
    }
    @Override
    public Apartment getById(int id) {
        return apartmentRepository.getById(id);
    }

    @Override
    public void update(Apartment apartment) {
        apartmentRepository.update(apartment);
    }

    @Override
    public void setStatus(int apartmentId, ApartmentStatus status) {
        Apartment apartment = apartmentRepository.getById(apartmentId);
        if (apartment != null) {
            apartment.setApartmentStatus(status);
            apartmentRepository.update(apartment);
        }
    }

    public void updatePrice(int apartmentId, BigDecimal newPrice) {
        Apartment apartment = apartmentRepository.getById(apartmentId);
        if (apartment != null) {
            apartment.setPrice(newPrice);
            apartmentRepository.update(apartment);
        }
    }

    @Override
    public boolean checkIfAvailable(Apartment apartment) {
        return apartment.getApartmentStatus() == ApartmentStatus.AVAILABLE;
    }

    @Override
    public boolean isValid(int apartmentId) {
        Apartment apartment = apartmentRepository.getById(apartmentId);
        return apartment != null;
    }

    @Override
    public List<Apartment> getAll() {
        return apartmentRepository.getAll();
    }

    public void add(Apartment apartment) {
        apartmentRepository.add(apartment);
    }
}
