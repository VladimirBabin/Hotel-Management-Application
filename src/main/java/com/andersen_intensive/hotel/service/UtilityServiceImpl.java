package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Utility;
import com.andersen_intensive.hotel.repository.UtilityRepository;

import java.util.Comparator;
import java.util.List;

public class UtilityServiceImpl implements UtilityService {

    private final UtilityRepository utilityRepository;

    public UtilityServiceImpl(UtilityRepository utilityRepository) {
        this.utilityRepository = utilityRepository;
    }

    @Override
    public Utility saveService(String name, double price) {
        Utility utility = new Utility(name, price);
        return utilityRepository.addService(utility);
    }

    @Override
    public List<Utility> sortByName() {
        List<Utility> sortedUtilities = utilityRepository.getAllServices();
        sortedUtilities.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        return sortedUtilities;
    }

    @Override
    public List<Utility> sortByPrice() {
        List<Utility> sortedUtilities = utilityRepository.getAllServices();
        sortedUtilities.sort(Comparator.comparingDouble(Utility::getPrice));
        return sortedUtilities;
    }
}
