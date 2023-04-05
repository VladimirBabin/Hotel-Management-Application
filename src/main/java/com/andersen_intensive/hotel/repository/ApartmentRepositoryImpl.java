package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;

import java.util.ArrayList;
import java.util.Comparator;
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
        for (Apartment apartment : apartments) {
            if (apartment.getApartmentNumber() == number) {
                return apartment;
            }
        }
        return null;
    }

    @Override
    public void updateApartmentPrice(Apartment apartment, double price) {
        apartment.setPrice(price);
    }

    @Override
    public List<Apartment> getListOfApartments() { //may be

        List<Apartment> apartmentsList = new ArrayList<>(apartments);//
        apartmentsList.sort(Comparator.comparing(Apartment::getApartmentPrice));//
        return apartmentsList;
    }

    @Override
    public void deleteApartment(Apartment apartment) {
        if (apartments.contains(apartment)) {
            apartments.remove(apartment);
        }
    }
    @Override
    public void setUnavailable(Apartment apartment) {
        apartment.setApartmentStatus(ApartmentStatus.UNAVAILABLE);
    }
}
