package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Utility;

import java.util.List;

public interface UtilityService {
    Utility saveService(String name, int price);
    List<Utility> sortByName();
    List<Utility> sortByPrice();
    void showAllUtilities();
    Utility getUtilityById(int utilityId);
    void changePrice(int utilityId, int newPrice);
}
