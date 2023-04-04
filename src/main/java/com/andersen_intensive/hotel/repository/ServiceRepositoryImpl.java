package com.andersen_intensive.hotel.repository;

import java.util.ArrayList;
import java.util.List;

public class ServiceRepositoryImpl implements ServiceRepository {

    List<Service> services;

    @Override
    public void addService(Service service) {
        if (services == null) {
            services = new ArrayList<Service>();
        }
        services.add(service);
    }

    @Override
    public List<Service> getListOfServices() {
        return null;
    }

    @Override
    public void changeServicePrice(double price) {

    }

    @Override
    public Service getByName(String name) {
        return null;
    }
}
