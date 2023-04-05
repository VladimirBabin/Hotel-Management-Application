package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Service;

import java.util.List;

public interface ServiceRepository {

    Service addService(Service service);

    Service getServiceById(int id);

    Service updateService(Service service);

    List<Service> getAllServices();

    Service getByName(String name);

    void deleteService(Service service);
}
