package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.repository.ApartmentRepository;

public class ApartmentServiceImpl implements ApartmentService {

    private ApartmentRepository apartmentRepository;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
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
