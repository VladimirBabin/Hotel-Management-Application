package com.andersen_intensive.hotel.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentTest {


    @Test
    void getApartmentId() {
        Apartment apartment = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        assertEquals(1, apartment.getApartmentId());
    }

    @Test
    void getApartmentPrice() {
        Apartment apartment = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        assertEquals(BigDecimal.valueOf(100), apartment.getApartmentPrice());
    }

    @Test
    void setPrice() {
        Apartment apartment = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        apartment.setPrice(BigDecimal.valueOf(150));
        assertEquals(BigDecimal.valueOf(150), apartment.getApartmentPrice());
    }

    @Test
    void getApartmentType() {
        Apartment apartment = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        assertEquals(ApartmentType.SINGLE, apartment.getApartmentType());
    }

    @Test
    void getApartmentStatus() {
        Apartment apartment = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        assertEquals(ApartmentStatus.AVAILABLE, apartment.getApartmentStatus());
    }

    @Test
    void setApartmentStatus() {
        Apartment apartment = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        apartment.setApartmentStatus(ApartmentStatus.UNAVAILABLE);
        assertEquals(ApartmentStatus.UNAVAILABLE, apartment.getApartmentStatus());
    }

    @Test
    void setApartmentType() {
        Apartment apartment = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        apartment.setApartmentType(ApartmentType.DOUBLE);
        assertEquals(ApartmentType.DOUBLE, apartment.getApartmentType());
    }

    @Test
    void ToString() {
        Apartment apartment = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        String expected = "Apartment number 1\nprice: $100\n" +
                "type apartment: SINGLE\nstatus: AVAILABLE\n";
        assertEquals(expected, apartment.toString());
    }

    @Test
    void Equals() {
        Apartment apartment1 = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        Apartment apartment2 = new Apartment(1, BigDecimal.valueOf(150), ApartmentType.DOUBLE);
        assertFalse(apartment1.equals(apartment2));
    }

    @Test
    void HashCode() {
        Apartment apartment1 = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        Apartment apartment2 = new Apartment(1, BigDecimal.valueOf(150), ApartmentType.DOUBLE);
        assertEquals(apartment1.hashCode(), apartment2.hashCode());
    }
}