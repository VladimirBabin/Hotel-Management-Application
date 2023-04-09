package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import com.andersen_intensive.hotel.repository.ApartmentRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentServiceImplTest {

    private ApartmentRepository apartmentRepository;
    private ApartmentService apartmentService;

    @BeforeEach
    void setUp() {
        apartmentRepository = new ApartmentRepositoryImpl();
        apartmentService = new ApartmentServiceImpl(apartmentRepository);
    }

    @Test
    void create() {
        Apartment apartment = apartmentService.create(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);

        assertNotNull(apartment);
        assertEquals(1, apartment.getApartmentId());
        assertEquals(BigDecimal.valueOf(100), apartment.getApartmentPrice());
        assertEquals(ApartmentType.SINGLE, apartment.getApartmentType());
        assertEquals(ApartmentStatus.AVAILABLE, apartment.getApartmentStatus());
    }

    @Test
    void getById() {
        Apartment apartment = apartmentService.create(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        Apartment foundApartment = apartmentService.getById(1);

        assertNotNull(foundApartment);
        assertEquals(apartment, foundApartment);
    }

    @Test
    void update() {
        Apartment apartment = apartmentService.create(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        apartment.setPrice(BigDecimal.valueOf(200));
        apartmentService.update(apartment);

        Apartment updatedApartment = apartmentService.getById(1);

        assertNotNull(updatedApartment);
        assertEquals(apartment, updatedApartment);
    }

    @Test
    void setStatus() {
        Apartment apartment = apartmentService.create(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        apartmentService.setStatus(1, ApartmentStatus.OCCUPIED);

        Apartment updatedApartment = apartmentService.getById(1);

        assertNotNull(updatedApartment);
        assertEquals(ApartmentStatus.OCCUPIED, updatedApartment.getApartmentStatus());
    }

    @Test
    void updatePrice() {
        Apartment apartment = apartmentService.create(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        apartmentService.updatePrice(1, BigDecimal.valueOf(200));

        Apartment updatedApartment = apartmentService.getById(1);

        assertNotNull(updatedApartment);
        assertEquals(BigDecimal.valueOf(200), updatedApartment.getApartmentPrice());
    }

    @Test
    void checkIfAvailable() {
        Apartment apartment = apartmentService.create(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        assertTrue(apartmentService.checkIfAvailable(apartment));

        apartmentService.setStatus(1, ApartmentStatus.OCCUPIED);
        assertFalse(apartmentService.checkIfAvailable(apartment));
    }

    @Test
    void isValid() {
        Apartment apartment = apartmentService.create(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        assertTrue(apartmentService.isValid(1));
        assertFalse(apartmentService.isValid(2));
    }

    @Test
    void getAll() {
        apartmentService.create(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        apartmentService.create(2, BigDecimal.valueOf(200), ApartmentType.DOUBLE);

        List<Apartment> apartments = apartmentService.getAll();

        assertNotNull(apartments);
        assertEquals(2, apartments.size());
    }

    @Test
    void add() {
        Apartment apartment = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        apartmentService.add(apartment);
        List<Apartment> apartments = apartmentService.getAll();
        assertEquals(1, apartments.size());
        assertEquals(apartment, apartments.get(0));
    }
}

