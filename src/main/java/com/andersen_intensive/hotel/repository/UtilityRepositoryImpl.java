package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilityRepositoryImpl implements UtilityRepository {

    private static Map<Integer, Utility> services = new HashMap<>();

    @Override
    public Utility addService(Utility utility) {
        services.put(utility.getId(), utility);
        return utility;
    }

    @Override
    public Utility getServiceById(int id) {
        return services.get(id);
    }

    @Override
    public Utility updateService(Utility utility) {
        services.put(utility.getId(), utility);
        return utility;
    }

    @Override
    public List<Utility> getAllServices() {
        return (List) services.values();
    }

    @Override
    public Utility getByName(String name) {
        for (Map.Entry<Integer, Utility> entry : services.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void deleteService(Utility utility) {
        if (services.containsValue(utility)) {
            services.remove(utility);
        }
    }
}
