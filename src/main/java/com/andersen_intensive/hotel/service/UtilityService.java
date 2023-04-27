package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.repository.UtilityRepository;
import com.andersen_intensive.hotel.models.Utility;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
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

    public List<Utility> sortByName() {
        List<Utility> sortedUtilities = utilityRepository.findAll();
        sortedUtilities.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        return sortedUtilities;
    }

    public List<Utility> sortByPrice() {
        List<Utility> sortedUtilities = utilityRepository.findAll();
        sortedUtilities.sort(Comparator.comparing(Utility::getPrice));
        return sortedUtilities;
    }

    public Utility changePrice(Long id, BigDecimal newPrice) {
        Optional<Utility> utilityFromMemory = utilityRepository.findById(id);

        if (utilityFromMemory.isEmpty()) {
            throw new EntityNotFoundException("Utility with this id does not exist");
        }

        if (newPrice.compareTo(BigDecimal.ZERO) < 0) { // price >= 0
            throw new IllegalArgumentException("Price cant be negative");
        }

        Utility utility = utilityFromMemory.get();
        utility.setPrice(newPrice);
        utilityRepository.save(utility);
        return utility;
    }
}
