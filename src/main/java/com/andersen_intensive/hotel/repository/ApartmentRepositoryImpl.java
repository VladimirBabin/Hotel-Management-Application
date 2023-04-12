package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;

import java.util.*;

public class ApartmentRepositoryImpl implements ApartmentRepository {
    private final Map<Integer, Apartment> apartments = new HashMap<>();

    @Override
    public Apartment add(Apartment apartment) {
        apartments.put(apartment.getApartmentId(), apartment);
        return apartment;
    }

    @Override
    public Apartment getById(int number) {
        return apartments.get(number);
    }

    @Override
    public void update(Apartment apartment) {
        apartments.put(apartment.getApartmentId(), apartment);
    }

    @Override

    public List<Apartment> getAll() {
        return new ArrayList<>(apartments.values());
    }

    @Override
    public void delete(Apartment apartment) {
        apartments.remove(apartment.getApartmentId());
    }
}
