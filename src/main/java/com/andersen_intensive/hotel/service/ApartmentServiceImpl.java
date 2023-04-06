package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import com.andersen_intensive.hotel.repository.ApartmentRepositoryImpl;


public class ApartmentServiceImpl implements ApartmentService {

    private ApartmentRepository apartmentRepository = ApartmentRepositoryImpl.getInstance();

    private static ApartmentServiceImpl INSTANCE;

    public static ApartmentServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApartmentServiceImpl();
        }
        return INSTANCE;
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
    public void updateApartmentPrice(int apartmentNumber, double newPrice) {
        Apartment apartment = apartmentRepository.getApartmentByNumber(apartmentNumber);
        if (apartment == null) {
            System.out.println("Apartment not found!");
            return;
        }
        apartment.setPrice(newPrice);
        apartmentRepository.updateApartment(apartment);

    }
}
