package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.repository.UtilityRepository;
import com.andersen_intensive.hotel.models.Utility;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UtilityService {

    private final UtilityRepository utilityRepository;

    public Utility saveUtility(Utility utility) {
        Utility utilityFromMemory = utilityRepository.findByName(utility.getName());

        if (utilityFromMemory != null) {
            throw new IllegalArgumentException("Utility with this name is already exist");
        }

        if (utility.getPrice().compareTo(BigDecimal.ZERO) < 0) { // price >= 0
            throw new IllegalArgumentException("Price cant be negative");
        }

//        Utility utility = new Utility(name, price);
        utilityRepository.save(utility);
        return utility;
    }

    public List<Utility> showAll() {
        return utilityRepository.findAll();
    }

    public Utility findUtilityById(Long id) {
        Optional<Utility> utilityFromMemory = utilityRepository.findById(id);
        if (utilityFromMemory.isEmpty()) {
            throw new EntityNotFoundException("Entity with this id does not exist");
        }
        return utilityFromMemory.get();
    }
}
