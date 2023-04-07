package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;

import java.util.*;

public class ApartmentRepositoryImpl implements ApartmentRepository {

    private static final Map<Integer, Apartment> apartments = new HashMap<>();

    @Override
    public Apartment addApartment(Apartment apartment) {
        apartments.put(apartment.getApartmentNumber(), apartment);
        return apartment;
    }

    @Override
    public Apartment getApartmentByNumber(int number) {
        return apartments.get(number);
    }

    @Override
    public Apartment updateApartment(Apartment apartment) {
        apartments.put(apartment.getApartmentNumber(), apartment);
        return apartment;
    }

    @Override

    public List<Apartment> getAllApartments() {
        return new ArrayList<>(apartments.values());

    }

    @Override
    public void deleteApartment(Apartment apartment) {
        apartments.values().removeIf(a -> a.equals(apartment));

    }

}
