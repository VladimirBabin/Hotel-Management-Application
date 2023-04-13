package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Utility;
import java.util.List;

public interface UtilityRepository {

    Utility addUtility(Utility utility);

    Utility getUtilityById(int id);

    Utility updateUtility(Utility utility);

    List<Utility> getAllUtility();

    Utility getByName(String name);

    void deleteUtility(Utility utility);
}
