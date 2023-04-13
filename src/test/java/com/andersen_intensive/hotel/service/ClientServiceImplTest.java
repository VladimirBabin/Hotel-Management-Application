package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ClientServiceImplTest {

    private static ClientService clientService;
    private static ClientRepository clientRepository;

    @BeforeAll
    static void setUp() {
        clientRepository = new ClientRepositoryImpl();
        clientService = new ClientServiceImpl(clientRepository);

        clientService.createClient("Max", "Muse", "+79211112233");
        clientService.createClient("Alex", "Moose", "+79211112244");
        clientService.createClient("David", "Mouse", "+79211112255");
    }

    @Test
    void createClientTest() {
        clientService.createClient("Seth", "Rogen", "+79211112266");
        assertEquals(4, clientRepository.getAllClients().size());
    }

    @Test
    void getClientListSortedByLastNameTest() {
        List<Client> expectedClients = new ArrayList<>();
        expectedClients.add(clientRepository.getClientById(2244));
        expectedClients.add(clientRepository.getClientById(2255));
        expectedClients.add(clientRepository.getClientById(2233));
        expectedClients.add(clientRepository.getClientById(2266));
        assertEquals(expectedClients, clientService.getClientListSortedByLastName());
    }

    @Test
    void getClientListSortedByID() {
        List<Client> expectedClients = new ArrayList<>();
        expectedClients.add(clientRepository.getClientById(2233));
        expectedClients.add(clientRepository.getClientById(2244));
        expectedClients.add(clientRepository.getClientById(2255));
        expectedClients.add(clientRepository.getClientById(2266));
        assertEquals(expectedClients, clientService.getClientListSortedByID());
    }

    @Test
    void getClientByIdTest() {
        assertEquals("Max", clientService.getClientByID(2233).getFirstName());
    }

    @Test
    void removeClientTest() {
        clientService.removeClient(2233);
        assertEquals(3, clientService.getClientListWithoutSorting().size());
    }
}