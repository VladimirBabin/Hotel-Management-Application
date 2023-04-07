package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Service;
import com.andersen_intensive.hotel.repository.ClientRepository;
import com.andersen_intensive.hotel.repository.ReservationRepositoryImpl;
import com.andersen_intensive.hotel.repository.ServiceRepository;
import com.andersen_intensive.hotel.repository.ServiceRepositoryImpl;

import java.util.Comparator;
import java.util.List;

public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Service saveService(String name, double price) {
        Service service = new Service(name, price);
        return serviceRepository.addService(service);
    }

    @Override
    public List<Service> sortByName() {
        List<Service> sortedServices = serviceRepository.getAllServices();
        sortedServices.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        return sortedServices;
    }

    @Override
    public List<Service> sortByPrice() {
        List<Service> sortedServices = serviceRepository.getAllServices();
        sortedServices.sort(Comparator.comparingDouble(Service::getPrice));
        return sortedServices;
    }
}
