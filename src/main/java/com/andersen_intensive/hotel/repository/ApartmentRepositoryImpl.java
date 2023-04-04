package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;

import java.util.ArrayList;
import java.util.List;

public class ApartmentRepositoryImpl implements ApartmentRepository {

    private List<Apartment> apartments;

    @Override
    public void addApartment(Apartment apartment) {
        if (apartments == null) {
            apartments = new ArrayList<Apartment>();
        }
        apartments.add(apartment);
    }

    @Override
    public Apartment getApartment(int number) {
        return null;
    }

    @Override
    public void updateApartmentPrice(Apartment apartment, double price) {

    }

    @Override
    public List<Apartment> getListOfApartments() {
        return null;
    }

    @Override
    public void deleteApartment(Apartment apartment) {

    }

    @Override
    public void setUnavailable(Apartment apartment) {

    }
}
