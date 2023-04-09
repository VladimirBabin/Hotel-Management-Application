package com.andersen_intensive.hotel.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void getFirstNameTest() {
        Client client = new Client("Andrew", "Werdna", "+79112324888");
        assertEquals("Andrew", client.getFirstName());
    }

    @Test
    void getLastNameTest() {
        Client client = new Client("Andrew", "Werdna", "+79112324888");
        assertEquals("Werdna", client.getLastName());
    }

    @Test
    void getPhoneNumberTest() {
        Client client = new Client("Andrew", "Werdna", "+79112324888");
        assertEquals("+79112324888", client.getPhoneNumber());
    }

    @Test
    void setFirstNameTest() {
        Client client = new Client("Andrew", "Werdna", "+79112324888");
        client.setFirstName("Anton");
        assertEquals("Anton", client.getFirstName());
    }

    @Test
    void setLastNameTest() {
        Client client = new Client("Andrew", "Werdna", "+79112324888");
        client.setLastName("Notna");
        assertEquals("Notna", client.getLastName());
    }

    @Test
    void setPhoneNumber() {
        Client client = new Client("Andrew", "Werdna", "+79112324888");
        client.setPhoneNumber("+79212324888");
        assertEquals("+79212324888", client.getPhoneNumber());
    }
}