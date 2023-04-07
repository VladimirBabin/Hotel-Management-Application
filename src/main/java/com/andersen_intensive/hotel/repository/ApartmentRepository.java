package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;

import java.util.List;

public interface ApartmentRepository {

    Apartment add(Apartment apartment);

    Apartment getById(int id);

    void update(Apartment apartment);

    List<Apartment> getAll();


    void delete(Apartment apartment);
}
