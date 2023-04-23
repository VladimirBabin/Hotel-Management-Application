package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Utility;
import com.andersen_intensive.hotel.repository.UtilityRepository;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void testSortByName() {
        List<Utility> utilities = Arrays.asList(
                new Utility("Water", new BigDecimal("20")),
                new Utility("Electricity", new BigDecimal("50")),
                new Utility("Gas", new BigDecimal("30"))
        );
        when(utilityRepository.findAll()).thenReturn(utilities);

        List<Utility> sortedUtilities = utilityService.sortByName();

        assertEquals("Electricity", sortedUtilities.get(0).getName());
        assertEquals("Gas", sortedUtilities.get(1).getName());
        assertEquals("Water", sortedUtilities.get(2).getName());
    }

    @Test
    public void testSortByPrice() {
        List<Utility> utilities = Arrays.asList(
                new Utility("Water", new BigDecimal("20")),
                new Utility("Electricity", new BigDecimal("50")),
                new Utility("Gas", new BigDecimal("30"))
        );
        when(utilityRepository.findAll()).thenReturn(utilities);

        List<Utility> sortedUtilities = utilityService.sortByPrice();

        assertEquals("Water", sortedUtilities.get(0).getName());
        assertEquals("Gas", sortedUtilities.get(1).getName());
        assertEquals("Electricity", sortedUtilities.get(2).getName());
    }

    @Test
    public void testChangePrice() {
        Utility utility = new Utility("Water", new BigDecimal("20"));
        when(utilityRepository.findById(any())).thenReturn(java.util.Optional.of(utility));

        Utility updatedUtility = utilityService.changePrice(1L, new BigDecimal("30.00"));

        assertEquals(new BigDecimal("30.00"), updatedUtility.getPrice());
    }

    @Test
    public void testChangePriceWithInvalidId() {
        when(utilityRepository.findById(any())).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> utilityService.changePrice(1L, new BigDecimal("30")));
    }

    @Test
    public void testChangePriceWithInvalidPrice() {
        Utility utility = new Utility("Water", new BigDecimal("20"));
        when(utilityRepository.findById(any())).thenReturn(java.util.Optional.of(utility));

        assertThrows(IllegalArgumentException.class, () -> utilityService.changePrice(1L, new BigDecimal("-30")));
    }
}