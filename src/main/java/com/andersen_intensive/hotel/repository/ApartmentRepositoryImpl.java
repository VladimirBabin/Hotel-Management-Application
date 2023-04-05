package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;

import java.util.*;

public class ApartmentRepositoryImpl implements ApartmentRepository {

    private static final ApartmentRepositoryImpl SINGLETON = new ApartmentRepositoryImpl();

    private static Map<Integer, Apartment> apartments = new HashMap<>();

    public static ApartmentRepositoryImpl getInstance() {
        return SINGLETON;
    }

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
        if (apartments.containsValue(apartment)) {
            apartments.remove(apartment);
        }
    }


    public void setUnavailable(Apartment apartment) {
        apartment.setApartmentStatus(ApartmentStatus.UNAVAILABLE);
    }
}
