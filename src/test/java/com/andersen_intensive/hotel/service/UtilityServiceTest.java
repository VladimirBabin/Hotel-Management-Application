package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Utility;
import com.andersen_intensive.hotel.repository.UtilityRepository;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilityServiceTest {

    private final UtilityRepository utilityRepository = mock(UtilityRepository.class);
    private final UtilityService utilityService = new UtilityService(utilityRepository);

    @Test
    public void testSaveUtilitySuccess() {
        Utility utility = new Utility("Electricity", new BigDecimal("100"));
        when(utilityRepository.findByName(utility.getName())).thenReturn(null);

        Utility savedUtility = utilityService.saveUtility(utility);

        verify(utilityRepository, times(1)).findByName(utility.getName());
        verify(utilityRepository, times(1)).save(utility);
        assertNotNull(savedUtility);
    }

    @Test
    public void testSaveUtilityWhenPriceIsNegative() {
        Utility utility = new Utility("Electricity", new BigDecimal("-100"));

        assertThrows(IllegalArgumentException.class, () -> utilityService.saveUtility(utility));
    }

    @Test
    public void testSaveUtilityAlreadyExist() {
        Utility utility = new Utility("Electricity", new BigDecimal("100"));
        when(utilityRepository.findByName(utility.getName())).thenReturn(utility);

        assertThrows(IllegalArgumentException.class, () -> utilityService.saveUtility(utility));
    }

    @Test
    public void testShowAll() {
        List<Utility> utilities = new ArrayList<>();
        utilities.add(new Utility("Electricity", new BigDecimal("100")));
        utilities.add(new Utility("Gas", new BigDecimal("50")));
        when(utilityRepository.findAll()).thenReturn(utilities);

        List<Utility> result = utilityService.showAll();
        assertEquals(utilities, result);
    }

    @Test
    public void testFindUtilityByIdSuccess() {
        Utility utility = new Utility("Electricity", new BigDecimal("100"));
        when(utilityRepository.findById(utility.getId())).thenReturn(Optional.of(utility));

        Utility result = utilityService.findUtilityById(utility.getId());
        assertEquals(utility, result);
    }

    @Test
    public void testFindUtilityByIdNotFound() {
        Long id = 1L;
        when(utilityRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> utilityService.findUtilityById(id));
    }
}