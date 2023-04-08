package com.andersen_intensive.hotel;

import static org.junit.jupiter.api.Assertions.*;

import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.service.ApartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import com.andersen_intensive.hotel.repository.ApartmentRepositoryImpl;

public class ApartmentUtilityTest {

    private ApartmentService apartmentService;
    private ApartmentRepository apartmentRepository;
    private Apartment apartment1;
    private Apartment apartment2;

    @BeforeEach
    public void setUp() {
        apartmentRepository = ApartmentRepositoryImpl.getInstance();
        apartmentService = ApartmentServiceImpl.getInstance();

        apartment1 = new Apartment(101, 100.0, ApartmentType.SINGLE);
        apartment2 = new Apartment(102, 150.0, ApartmentType.DOUBLE);

        apartmentRepository.addApartment(apartment1);
        apartmentRepository.addApartment(apartment2);
    }

    @Test
    public void testGetApartmentByNumber() {
        Apartment apartment = apartmentService.getApartmentByNumber(101);
        assertEquals(apartment, apartment1);
    }

    @Test
    public void testUpdate() {
        apartment1.setPrice(120.0);
        apartmentService.update(apartment1);

        Apartment updatedApartment = apartmentService.getApartmentByNumber(101);
        assertEquals(updatedApartment.getApartmentNumber(), 101.0);
    }

    @Test
    public void testSetApartmentStatus() {
        apartmentService.setApartmentStatus(101, ApartmentStatus.OCCUPIED);

        Apartment updatedApartment = apartmentService.getApartmentByNumber(101);
        assertEquals(updatedApartment.getApartmentStatus(), ApartmentStatus.OCCUPIED);
    }

    @Test
    public void testUpdateApartmentPrice() {
        apartmentService.updateApartmentPrice(101, 120.0);

        Apartment updatedApartment = apartmentService.getApartmentByNumber(101);
        assertEquals(updatedApartment.getApartmentPrice(), 120.0);
    }

    @Test
    public void testCheckIfAvailable() {
        boolean isAvailable = apartmentService.checkIfAvailable(apartment1);
        assertTrue(isAvailable);

        apartmentService.setApartmentStatus(101, ApartmentStatus.OCCUPIED);

        isAvailable = apartmentService.checkIfAvailable(apartment1);
        assertFalse(isAvailable);
    }

    @Test
    public void testIsValidApartment() {
        boolean isValid = apartmentService.isValidApartment(101);
        assertTrue(isValid);

        isValid = apartmentService.isValidApartment(103);
        assertFalse(isValid);
    }
}
