package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceRepositoryImpl implements ServiceRepository {

    private static final ServiceRepositoryImpl SINGLETON = new ServiceRepositoryImpl();

    private static Map<Integer, Service> services = new HashMap<>();

    public static ServiceRepositoryImpl getInstance() {
        return SINGLETON;
    }

    @Override
    public Service addService(Service service) {
        services.put(service.getId(), service);
        return service;
    }

    @Override
    public Service getServiceById(int id) {
        return services.get(id);
    }

    @Override
    public Service updateService(Service service) {
        services.put(service.getId(), service);
        return service;
    }

    @Override
    public List<Service> getAllServices() {
        return (List) services.values();
    }

    @Override
    public Service getByName(String name) {
        for (Map.Entry<Integer, Service> entry : services.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void deleteService(Service service) {
        if (services.containsValue(service)) {
            services.remove(service);
        }
    }
}
