//package com.andersen_intensive.hotel.DEPRECATEDservice;
//
//import com.andersen_intensive.hotel.models.Utility;
//import com.andersen_intensive.hotel.DEPRECATEDrepository.UtilityRepositoryImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//class UtilityServiceImplTest {
//
//    private final UtilityRepositoryImpl utilityRepositoryTest = new UtilityRepositoryImpl();
//    private final UtilityServiceImpl utilityService = new UtilityServiceImpl(utilityRepositoryTest);
//
//    @BeforeEach
//    void setUp() {
//        utilityService.saveService(1, "Ironing", new BigDecimal(100));
//        utilityService.saveService(2, "Laundry", new BigDecimal(200));
//        utilityService.saveService(3, "Champagne", new BigDecimal(50));
//    }
//
//    @Test
//    void saveServiceTest() {
//        utilityService.saveService(4, "Shoeshine", new BigDecimal(150));
//        assertEquals(4, utilityRepositoryTest.getAllUtility().size());
//    }
//
//    @Test
//    void wrongSaveServiceTest() {
//        assertThrows(IllegalArgumentException.class,
//                () -> utilityService.saveService(5, "Valet", new BigDecimal(-100)));
//    }
//
//    @Test
//    void sortByNameTest() {
//        List<Utility> expectedUtilities = new ArrayList<>();
//        expectedUtilities.add(utilityRepositoryTest.getUtilityById(3));
//        expectedUtilities.add(utilityRepositoryTest.getUtilityById(1));
//        expectedUtilities.add(utilityRepositoryTest.getUtilityById(2));
//        assertEquals(expectedUtilities, utilityService.sortByName());
//    }
//
//    @Test
//    void sortByPriceTest() {
//        List<Utility> expectedUtilities = new ArrayList<>();
//        expectedUtilities.add(utilityService.getUtilityById(3));
//        expectedUtilities.add(utilityService.getUtilityById(1));
//        expectedUtilities.add(utilityService.getUtilityById(2));
//        assertEquals(expectedUtilities, utilityService.sortByPrice());
//    }
//
//    @Test
//    void getUtilityByIdTest() {
//        assertEquals("Ironing", utilityService.getUtilityById(1).getName());
//    }
//
//    @Test
//    void changePriceTest() {
//        utilityService.changePrice(2, new BigDecimal(1000));
//        assertEquals(new BigDecimal(1000), utilityService.getUtilityById(2).getPrice());
//    }
//}