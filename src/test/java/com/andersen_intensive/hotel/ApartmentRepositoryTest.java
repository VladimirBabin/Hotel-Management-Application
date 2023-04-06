package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.repository.ApartmentRepositoryImpl;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentRepositoryTest {

    private ApartmentRepositoryImpl apartmentRepository;

    @BeforeEach
    void setUp() {
        apartmentRepository = ApartmentRepositoryImpl.getInstance();
        apartmentRepository.addApartment(new Apartment(101, 100.0, ApartmentType.SINGLE));
        apartmentRepository.addApartment(new Apartment(102, 200.0, ApartmentType.DOUBLE));
    }

    @Test
    void testAddApartment() {
        Apartment newApartment = new Apartment(103, 150.0, ApartmentType.SINGLE);
        apartmentRepository.addApartment(newApartment);
        assertEquals(newApartment, apartmentRepository.getApartmentByNumber(103));
    }

    @Test
    void testGetApartmentByNumber() {
        Apartment apartment = apartmentRepository.getApartmentByNumber(101);
        assertNotNull(apartment);
        assertEquals(101, apartment.getApartmentNumber());
    }

    @Test
    void testGetAllApartments() {
        List<Apartment> apartments = apartmentRepository.getAllApartments();
        assertNotNull(apartments);
        assertEquals(2, apartments.size());
    }

    @Test
    void testUpdateApartment() {
        Apartment apartment = apartmentRepository.getApartmentByNumber(101);
        apartment.setPrice(150.0);
        apartmentRepository.updateApartment(apartment);
        assertEquals(150.0, apartmentRepository.getApartmentByNumber(101).getApartmentPrice());
    }

    @Test
    void testDeleteApartment() {
        Apartment apartment = apartmentRepository.getApartmentByNumber(101);
        assertNotNull(apartment);
        apartmentRepository.deleteApartment(apartment);
        assertNull(apartmentRepository.getApartmentByNumber(101));
    }
}
