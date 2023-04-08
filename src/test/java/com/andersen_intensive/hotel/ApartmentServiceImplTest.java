package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.service.ApartmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApartmentServiceImplTest {

    private ApartmentService apartmentService;

    @BeforeEach
    void setUp() {

        ApartmentRepository apartmentRepository = new ApartmentRepository() {
            private final List<Apartment> apartments = new ArrayList<>();

            @Override
            public Apartment add(Apartment apartment) {
                apartments.add(apartment);
                return apartment;
            }

            @Override
            public Apartment getById(int id) {
                return apartments.stream()
                        .filter(a -> a.getApartmentId() == id)
                        .findFirst()
                        .orElse(null);
            }

            @Override
            public void update(Apartment apartment) {
                // do nothing
            }

            @Override
            public List<Apartment> getAll() {
                return apartments;
            }

            @Override
            public void delete(Apartment apartment) {

            }
        };

        apartmentService = new ApartmentServiceImpl(apartmentRepository);
    }

    @Test
    void testCreate() {
        Apartment apartment = apartmentService.create(1, new BigDecimal("100.00"), ApartmentType.SINGLE);
        Assertions.assertEquals(1, apartment.getApartmentId());
        Assertions.assertEquals(new BigDecimal("100.00"), apartment.getApartmentPrice());
        Assertions.assertEquals(ApartmentType.SINGLE, apartment.getApartmentType());
    }

    @Test
    void testGetById() {
        apartmentService.create(1, new BigDecimal("100.00"), ApartmentType.SINGLE);
        Apartment apartment = apartmentService.getById(1);
        Assertions.assertEquals(1, apartment.getApartmentId());
    }

    @Test
    void testUpdate() {
        Apartment apartment = apartmentService.create(1, new BigDecimal("100.00"), ApartmentType.SINGLE);
        apartment.setApartmentType(ApartmentType.DOUBLE);
        apartmentService.update(apartment);
        Apartment updatedApartment = apartmentService.getById(1);
        Assertions.assertEquals(ApartmentType.DOUBLE, updatedApartment.getApartmentType());
    }

    @Test
    void testSetStatus() {
        Apartment apartment = apartmentService.create(1, new BigDecimal("100.00"), ApartmentType.SINGLE);
        apartmentService.setStatus(apartment.getApartmentId(), ApartmentStatus.UNAVAILABLE);
        Apartment updatedApartment = apartmentService.getById(1);
        Assertions.assertEquals(ApartmentStatus.UNAVAILABLE, updatedApartment.getApartmentStatus());
    }

    @Test
    void testUpdatePrice() {
        Apartment apartment = apartmentService.create(1, new BigDecimal("100.00"), ApartmentType.SINGLE);
        apartmentService.updatePrice(apartment.getApartmentId(), new BigDecimal("150.00"));
        Apartment updatedApartment = apartmentService.getById(1);
        Assertions.assertEquals(new BigDecimal("150.00"), updatedApartment.getApartmentPrice());
    }

    @Test
    void testCheckIfAvailable() {

        Apartment apartment1 = new Apartment(1, new BigDecimal("100"), ApartmentType.SINGLE);
        apartment1.setApartmentStatus(ApartmentStatus.AVAILABLE);
        Apartment apartment2 = new Apartment(2, new BigDecimal("150"), ApartmentType.DOUBLE);
        apartment2.setApartmentStatus(ApartmentStatus.OCCUPIED);

        boolean result1 = apartmentService.checkIfAvailable(apartment1);
        boolean result2 = apartmentService.checkIfAvailable(apartment2);

        assertTrue(result1);
        assertFalse(result2);
    }

}


