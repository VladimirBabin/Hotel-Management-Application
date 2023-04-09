package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class ClientServiceImplTest {

    private ClientService clientService;

    @BeforeEach
    void setUp() {

        ClientRepository clientRepository = new ClientRepository() {
            private final List<Client> clients = new ArrayList<>();

            @Override
            public Client addClient(Client client) {
                clients.add(client);
                return client;
            }

            @Override
            public Client getClientById(int id) {
                return clients.stream()
                        .filter(a -> a.getPersonalID() == id)
                        .findFirst()
                        .orElse(null);
            }

            @Override
            public List<Client> getAllClients() {
                return clients;
            }

            @Override
            public Client updateClient(Client client) {
                return null;
            }

            @Override
            public void deleteClient(int id) {

            }
        };
        clientService = new ClientServiceImpl(clientRepository);
    }

    @Test
    void createClientTest() {
        Client client = clientService.createClient("Vlad", "Pirozkov", "+79213334455");
        Assertions.assertEquals(1, client.getPersonalID());
        Assertions.assertEquals("Vlad", client.getFirstName());
        Assertions.assertEquals("Pirozkov", client.getLastName());
        Assertions.assertEquals("+79213334455", client.getPhoneNumber());
    }

    @Test
    void getClientByIDTest() {
        Client client = clientService.createClient("Vlad", "Pirozkov", "+79213334455");
        Client client1 = clientService.getClientByID(1);
        Assertions.assertEquals(client, client1);
    }

    @Test
    void getClientListTest() {
        assertTrue(clientService.getClientList(false).isEmpty());

        clientService.createClient("Andrew", "Werdna", "+79112324888");
        clientService.createClient("Alex", "Xela", "+79212324888");
        clientService.createClient("Roman", "Narom", "+79991112233");

        assertFalse(clientService.getClientList(false).isEmpty());
        assertEquals(3, clientService.getClientList(false).size());
    }

    @Test
    void removeClientTest() {
    }

    @Test
    void updateClientTest() {
    }
}