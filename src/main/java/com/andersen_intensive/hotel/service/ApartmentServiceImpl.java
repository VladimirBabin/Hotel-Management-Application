package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import com.andersen_intensive.hotel.repository.ApartmentRepositoryImpl;
import com.andersen_intensive.hotel.repository.ServiceRepositoryImpl;

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

}
