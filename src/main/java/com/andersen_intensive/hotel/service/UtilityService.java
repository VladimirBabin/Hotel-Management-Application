package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Utility;
import java.math.BigDecimal;
import java.util.List;

public interface UtilityService {

    Utility saveService(int id, String name, BigDecimal price);

    List<Utility> sortByName();

    List<Utility> sortByPrice();

    List<Utility> showAllUtilities();

    Utility getUtilityById(int utilityId);

    void changePrice(int utilityId, BigDecimal newPrice);
}
