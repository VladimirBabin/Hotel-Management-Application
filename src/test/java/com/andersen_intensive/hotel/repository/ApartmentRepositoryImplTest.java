package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentRepositoryImplTest {
    private ApartmentRepositoryImpl apartmentRepository;
    private Apartment apartment1;
    private Apartment apartment2;
    private Apartment apartment3;

    @BeforeEach
    void setUp() {
        apartmentRepository = new ApartmentRepositoryImpl();
        apartment1 = new Apartment(1, new BigDecimal("100"), ApartmentType.SINGLE);
        apartment2 = new Apartment(2, new BigDecimal("150"), ApartmentType.DOUBLE);
        apartment3 = new Apartment(3, new BigDecimal("200"), ApartmentType.SINGLE);
    }

    @Test
    void add() {
        assertNull(apartmentRepository.getById(1));
        apartmentRepository.add(apartment1);
        assertNotNull(apartmentRepository.getById(1));
        assertEquals(apartment1, apartmentRepository.getById(1));
    }

    @Test
    void getById() {
        assertNull(apartmentRepository.getById(1));
        apartmentRepository.add(apartment1);
        assertNotNull(apartmentRepository.getById(1));
        assertEquals(apartment1, apartmentRepository.getById(1));
    }

    @Test
    void update() {
        assertNull(apartmentRepository.getById(1));
        apartmentRepository.add(apartment1);
        assertNotNull(apartmentRepository.getById(1));
        assertEquals(apartment1, apartmentRepository.getById(1));
        apartment1.setPrice(new BigDecimal("200"));
        apartment1.setApartmentStatus(ApartmentStatus.OCCUPIED);
        apartmentRepository.update(apartment1);
        assertNotNull(apartmentRepository.getById(1));
        assertEquals(apartment1, apartmentRepository.getById(1));
    }

    @Test
    void getAll() {
        assertTrue(apartmentRepository.getAll().isEmpty());
        apartmentRepository.add(apartment1);
        apartmentRepository.add(apartment2);
        apartmentRepository.add(apartment3);
        assertFalse(apartmentRepository.getAll().isEmpty());
        assertEquals(3, apartmentRepository.getAll().size());
    }

    @Test
    void delete() {
        apartmentRepository.add(apartment1);
        assertNotNull(apartmentRepository.getById(1));
        apartmentRepository.delete(apartment1);
        assertNull(apartmentRepository.getById(1));
    }
}
