package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.repository.ApartmentRepository;

import java.math.BigDecimal;

import java.math.BigDecimal;


public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public Apartment createApartment(int apartmentNumber, BigDecimal price, ApartmentType apartmentType) {
        Apartment apartment = new Apartment(apartmentNumber, price, apartmentType);
        return apartmentRepository.addApartment(apartment);
    }

    @Override
    public Apartment getApartmentByNumber(int number) {
        return apartmentRepository.getApartmentByNumber(number);
    }

    @Override
    public void update(Apartment apartment) {
        apartmentRepository.updateApartment(apartment);
    }
    @Override
    public void setApartmentStatus(int apartmentNumber, ApartmentStatus status) {
        Apartment apartment = apartmentRepository.getApartmentByNumber(apartmentNumber);
        if (apartment != null) {
            apartment.setApartmentStatus(status);
            apartmentRepository.updateApartment(apartment);
        }
    }
    public void updateApartmentPrice(int apartmentNumber, BigDecimal newPrice) {
        Apartment apartment = apartmentRepository.getApartmentByNumber(apartmentNumber);
        if (apartment == null) {
            System.out.println("Apartment not found!");
            return;
        }
        apartment.setPrice(newPrice);
        apartmentRepository.updateApartment(apartment);

    }
    @Override
    public boolean checkIfAvailable(Apartment apartment) {
        if (apartment.getApartmentStatus() == ApartmentStatus.AVAILABLE) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean isValidApartment(int apartmentNumber) {
        Apartment apartment = apartmentRepository.getApartmentByNumber(apartmentNumber);
        return apartment != null;
    }
}
