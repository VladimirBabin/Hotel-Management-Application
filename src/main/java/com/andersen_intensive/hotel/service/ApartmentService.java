package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;

import java.math.BigDecimal;

import java.util.List;

public interface ApartmentService {

    Apartment create(int apartmentId, BigDecimal price, ApartmentType apartmentType);

    void add(Apartment apartment);

    Apartment getById(int id);

    List<Apartment> getAll();

    void update(Apartment apartment);

    void setStatus(int apartmentId, ApartmentStatus status);

    void updatePrice(int apartmentId, BigDecimal newPrice);

    boolean checkIfAvailable(Apartment apartment);

    boolean isValid(int apartmentId);

}
