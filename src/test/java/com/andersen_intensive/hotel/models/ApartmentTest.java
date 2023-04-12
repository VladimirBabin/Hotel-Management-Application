package com.andersen_intensive.hotel.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentTest {
    @Test
    void ToString() {
        Apartment apartment = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        String expected = """
                Apartment number 1
                price: $100
                type apartment: SINGLE
                status: AVAILABLE
                """;
        assertEquals(expected, apartment.toString());
    }

    @Test
    void Equals() {
        Apartment apartment1 = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        Apartment apartment2 = new Apartment(1, BigDecimal.valueOf(150), ApartmentType.DOUBLE);

        assertNotEquals(apartment1, apartment2);
    }

    @Test
    void HashCode() {
        Apartment apartment1 = new Apartment(1, BigDecimal.valueOf(100), ApartmentType.SINGLE);
        Apartment apartment2 = new Apartment(1, BigDecimal.valueOf(150), ApartmentType.DOUBLE);

        assertEquals(apartment1.hashCode(), apartment2.hashCode());
    }
}