package com.andersen_intensive.hotel.repository;

public interface ServiceRepository {

    void addService(Service service);

    List<Service> getListOfServices();

    void changeServicePrice(double price);

    Service getByName(String name);
}
