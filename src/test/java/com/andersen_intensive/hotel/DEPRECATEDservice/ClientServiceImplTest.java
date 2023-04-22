package com.andersen_intensive.hotel.DEPRECATEDservice;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.DEPRECATEDrepository.*;
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

        clientService.createClient("Max", "Abc", "+79211112233", 111);
        clientService.createClient("Alex", "Def", "+79211112244", 222);
        clientService.createClient("David", "Ghi", "+79211112255", 444);
    }

    @Test
    void createClientTest() {
        clientService.createClient("Seth", "Rogen", "+79211112266", 333);
        assertEquals(4, clientRepository.getAllClients().size());
    }

    @Test
    void getClientListSortedByLastNameTest() {
        List<Client> expectedClients = new ArrayList<>();
        expectedClients.add(clientRepository.getClientById(111));
        expectedClients.add(clientRepository.getClientById(222));
        expectedClients.add(clientRepository.getClientById(444));
        expectedClients.add(clientRepository.getClientById(333));
        assertEquals(expectedClients, clientService.getClientListSortedByLastName());
    }

    @Test
    void getClientListSortedByID() {
        List<Client> expectedClients = new ArrayList<>();
        expectedClients.add(clientRepository.getClientById(111));
        expectedClients.add(clientRepository.getClientById(222));
        expectedClients.add(clientRepository.getClientById(333));
        expectedClients.add(clientRepository.getClientById(444));
        assertEquals(expectedClients, clientService.getClientListSortedByID());
    }

    @Test
    void getClientByIdTest() {
        assertEquals("Max", clientService.getClientByID(111).getFirstName());
    }

    @Test
    void removeClientTest() {
        clientService.removeClient(333);
        assertEquals(3, clientService.getClientListWithoutSorting().size());
    }
}