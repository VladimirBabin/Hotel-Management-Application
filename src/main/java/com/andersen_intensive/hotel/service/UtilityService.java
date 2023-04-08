package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Utility;

import java.math.BigDecimal;
import java.util.List;

public interface UtilityService {
    Utility saveService(String name, BigDecimal price);
    List<Utility> sortByName();
    List<Utility> sortByPrice();
}
