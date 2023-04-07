package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Utility;

import java.util.List;

public interface UtilityRepository {

    Utility addService(Utility utility);

    Utility getServiceById(int id);

    Utility updateService(Utility utility);

    List<Utility> getAllServices();

    Utility getByName(String name);

    void deleteService(Utility utility);
}
