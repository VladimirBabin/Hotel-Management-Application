package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Service;

import java.util.List;

public interface ServiceService {
    Service saveService(String name, double price);
    List<Service> sortByName();
    List<Service> sortByPrice();
}
