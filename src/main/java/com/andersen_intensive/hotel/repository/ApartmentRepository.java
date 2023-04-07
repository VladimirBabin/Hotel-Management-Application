package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;

import java.util.List;

public interface ApartmentRepository {

    Apartment addApartment(Apartment apartment);

    Apartment getApartmentByNumber(int number);

    Apartment updateApartment(Apartment apartment);

    List<Apartment> getAllApartments();

    void deleteApartment(Apartment apartment);
}
