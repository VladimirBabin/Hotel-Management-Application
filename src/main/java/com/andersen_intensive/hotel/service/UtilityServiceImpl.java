package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Utility;
import com.andersen_intensive.hotel.repository.UtilityRepository;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class UtilityServiceImpl implements UtilityService {

    private final UtilityRepository utilityRepository;

    public UtilityServiceImpl(UtilityRepository utilityRepository) {
        this.utilityRepository = utilityRepository;
    }

    @Override
    public Utility saveService(String name, int price) {
        Utility utility = new Utility(name, price);
        return utilityRepository.addUtility(utility);
    }

    @Override
    public List<Utility> sortByName() {
        List<Utility> sortedUtilities = utilityRepository.getAllUtility();
        sortedUtilities.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        return sortedUtilities;
    }

    @Override
    public List<Utility> sortByPrice() {
        List<Utility> sortedUtilities = utilityRepository.getAllUtility();
        sortedUtilities.sort(Comparator.comparingDouble(Utility::getPrice));
        return sortedUtilities;
    }

    @Override
    public void showAllUtilities() {
        List<Utility> utilities = utilityRepository.getAllUtility();
        System.out.println(utilities);
    }

    @Override
    public Utility getUtilityById(int utilityId) {
        Utility utility = utilityRepository.getUtilityById(utilityId);
        if (utility == null){
            return null;
        }
        return utility;
    }

    @Override
    public void changePrice(int utilityId, int newPrice) {
        Utility utility = utilityRepository.getUtilityById(utilityId);
        utility.setPrice(newPrice);
    }
}
