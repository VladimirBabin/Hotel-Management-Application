package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;

import java.math.BigDecimal;

public interface ApartmentService {
    Apartment getApartmentByNumber(int number);

    void update(Apartment apartment);

    void setApartmentStatus(int apartmentNumber, ApartmentStatus status);
    void updateApartmentPrice(int apartmentNumber, BigDecimal newPrice);
    boolean checkIfAvailable(Apartment apartment);
    boolean isValidApartment(int apartmentNumber);
}
