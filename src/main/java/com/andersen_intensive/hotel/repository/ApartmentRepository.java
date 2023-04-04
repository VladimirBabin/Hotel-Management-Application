package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;

import java.util.List;

public interface ApartmentRepository {
    void addApartment(Apartment apartment);

    Apartment getApartment(int number);

    void updateApartmentPrice(Apartment apartment, double price);

    List<Apartment> getListOfApartments();

    void deleteApartment(Apartment apartment);

    void setUnavailable(Apartment apartment);
}
