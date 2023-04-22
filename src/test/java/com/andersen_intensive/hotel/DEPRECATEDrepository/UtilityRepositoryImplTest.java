package com.andersen_intensive.hotel.DEPRECATEDrepository;

import com.andersen_intensive.hotel.models.Utility;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilityRepositoryImplTest {

    private final UtilityRepositoryImpl utilityRepository = new UtilityRepositoryImpl();

    @BeforeEach
    void setUp() {
        Utility utility1 = new Utility(1, "Laundry", new BigDecimal(150));
        Utility utility2 = new Utility(2, "Ironing", new BigDecimal(50));
        Utility utility3 = new Utility(3, "Valet", new BigDecimal(500));
        Utility utility4 = new Utility(4, "Shoeshine", new BigDecimal(200));

        utilityRepository.addUtility(utility1);
        utilityRepository.addUtility(utility2);
        utilityRepository.addUtility(utility3);
        utilityRepository.addUtility(utility4);
    }

    @Test
    void addUtilityTest() {
        Utility newUtility = new Utility(5, "Something", new BigDecimal(150));
        utilityRepository.addUtility(newUtility);
        assertEquals(5, utilityRepository.getAllUtility().size());
    }

    @Test
    void getUtilityByIdTest() {
        Utility utility = utilityRepository.getUtilityById(2);
        assertEquals("Ironing", utility.getName());
    }

    @Test
    void updateUtilityTest() {
        Utility utility = utilityRepository.getUtilityById(3);
        utility.setName("Mail");
        utilityRepository.updateUtility(utility);
        assertEquals("Mail", utilityRepository.getByName("Mail").getName());
    }

    @Test
    void getAllUtilityTest() {
        assertEquals(4, utilityRepository.getAllUtility().size());
    }

    @Test
    void getByNameTest() {
        assertEquals(4, utilityRepository.getByName("Shoeshine").getId());
    }

    @Test
    void deleteUtilityTest() {
        Utility utility = utilityRepository.getUtilityById(3);
        utilityRepository.deleteUtility(utility);
        assertEquals(3, utilityRepository.getAllUtility().size());
    }
}