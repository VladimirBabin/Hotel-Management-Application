package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;


class ClientRepositoryImplTest {

    private static Map<UUID, Client> clients = new HashMap<>();
    private ClientRepositoryImpl clientRepository;
    private Client client1;
    private Client client2;
    private Client client3;

    @BeforeEach
    void setUp() {
        client1 = new Client("Andrew", "Werdna", "+79112324888");
        client2 = new Client("Alex", "Xela", "+79212324888");
        client3 = new Client("Roman", "Narom", "+79991112233");
    }

    @Test
    void addClientTest() {
        assertNull(clientRepository.getClientById(1));
        clientRepository.addClient(client1);
        assertNotNull(clientRepository.getClientById(1));
        assertEquals(client1, clientRepository.getClientById(1));
    }

    @Test
    void getClientByIdTest() {
        assertNull(clientRepository.getClientById(1));
        clientRepository.addClient(client1);
        assertNotNull(clientRepository.getClientById(1));
        assertEquals(client1, clientRepository.getClientById(1));
    }

    @Test
    void getAllClientsTest() {
        assertTrue(clientRepository.getAllClients().isEmpty());

        clientRepository.addClient(client1);
        clientRepository.addClient(client2);
        clientRepository.addClient(client3);

        assertFalse(clientRepository.getAllClients().isEmpty());
        assertEquals(3, clientRepository.getAllClients().size());

    }

    @Test
    void updateClientTest() {
        assertNull(clientRepository.getClientById(1));
        clientRepository.addClient(client1);
        assertNotNull(clientRepository.getClientById(1));
        assertEquals(client1, clientRepository.getClientById(1));

        client1.setFirstName("Karl");
        client1.setLastName("Lrak");
        client1.setPhoneNumber("+79112223344");
        clientRepository.updateClient(client1);

        assertNotNull(clientRepository.getClientById(1));
        assertEquals(client1, clientRepository.getClientById(1));
    }

    @Test
    void deleteClientTest() {
        clientRepository.addClient(client1);
        assertNotNull(clientRepository.getClientById(1));
        clientRepository.deleteClient(1);
        assertNull(clientRepository.getClientById(1));
    }
}

