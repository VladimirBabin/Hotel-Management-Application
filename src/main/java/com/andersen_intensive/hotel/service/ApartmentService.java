package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;

public interface ApartmentService {
    Apartment getApartmentByNumber(int number);

    void update(Apartment apartment);

    void setApartmentStatus(int apartmentNumber, ApartmentStatus status);
    void updateApartmentPrice(int apartmentNumber, double newPrice);
    boolean checkIfAvailable(Apartment apartment);
    boolean isValidApartment(int apartmentNumber);
}
