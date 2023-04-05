package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;

public interface ApartmentService {
    Apartment getApartmentByNumber(int number);

    void update(Apartment apartment);
}
