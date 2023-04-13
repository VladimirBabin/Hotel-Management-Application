package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Utility;
import com.andersen_intensive.hotel.repository.UtilityRepository;
import com.andersen_intensive.hotel.repository.UtilityRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilityServiceImplTest {

    private static UtilityService utilityService;
    private static UtilityRepository utilityRepository;

    @BeforeAll
    static void setUp() {
        utilityRepository = new UtilityRepositoryImpl();
        utilityService = new UtilityServiceImpl(utilityRepository);

        utilityService.saveService("Ironing", new BigDecimal(100));
        utilityService.saveService("Laundry", new BigDecimal(200));
        utilityService.saveService("Champagne", new BigDecimal(50));
    }

    @Test
    void saveServiceTest() {
        utilityService.saveService("Shoeshine", new BigDecimal(150));
        assertEquals(4, utilityRepository.getAllUtility().size());
    }

    @Test
    void wrongSaveServiceTest() {
        assertThrows(IllegalArgumentException.class,
                () -> utilityService.saveService("Valet", new BigDecimal(-100)));
    }

    @Test
    void sortByNameTest() {
        List<Utility> expectedUtilities = new ArrayList<>();
        expectedUtilities.add(utilityRepository.getUtilityById(3));
        expectedUtilities.add(utilityRepository.getUtilityById(1));
        expectedUtilities.add(utilityRepository.getUtilityById(2));
        expectedUtilities.add(utilityRepository.getUtilityById(4));
        assertEquals(expectedUtilities, utilityService.sortByName());
    }

    @Test
    void sortByPriceTest() {
        List<Utility> expectedUtilities = new ArrayList<>();
        expectedUtilities.add(utilityRepository.getUtilityById(3));
        expectedUtilities.add(utilityRepository.getUtilityById(1));
        expectedUtilities.add(utilityRepository.getUtilityById(2));
        assertEquals(expectedUtilities, utilityService.sortByPrice());
    }

    @Test
    void getUtilityByIdTest() {
        assertEquals("Ironing", utilityService.getUtilityById(1).getName());
    }

    @Test
    void changePriceTest() {
        utilityService.changePrice(2, new BigDecimal(1000));
        assertEquals(new BigDecimal(1000), utilityService.getUtilityById(2).getPrice());
    }
}