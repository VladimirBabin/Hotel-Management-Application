package com.andersen_intensive.hotel.DEPRECATEDrepository;

import com.andersen_intensive.hotel.models.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientRepositoryImplTest {

    private static ClientRepository clientRepository;

    @BeforeAll
    static void setUp() {
        clientRepository = new ClientRepositoryImpl();

        Client client1 = new Client("Mariana", "Arsene", "+79112324999", 111);
        Client client2 = new Client("Roman", "Sorokin", "+79112324911", 222);
        Client client3 = new Client("Daria", "Rongonen", "+79112324922", 333);
        Client client4 = new Client("Viktoria", "Madalina", "+79112324933", 444);

        clientRepository.addClient(client1);
        clientRepository.addClient(client2);
        clientRepository.addClient(client3);
        clientRepository.addClient(client4);
    }

    @Test
    void addClientTest() {
        Client client5 = new Client("Vera", "Svalbova", "+79211112233", 555);
        clientRepository.addClient(client5);
        assertEquals(4, clientRepository.getAllClients().size());
    }

    @Test
    void getClientByIdTest() {
        assertEquals("Alexander", clientRepository.getClientById(222).getFirstName());
    }

    @Test
    void updateClientTest() {
        clientRepository.getClientById(222).setFirstName("Alexander");
        clientRepository.updateClient(clientRepository.getClientById(222));
        assertEquals("Alexander", clientRepository.getClientById(222).getFirstName());
    }

    @Test
    void getAllClientTest() {
        assertEquals(4, clientRepository.getAllClients().size());
    }

    @Test
    void deleteClientTest() {
        clientRepository.deleteClient(111);
        assertEquals(3, clientRepository.getAllClients().size());
    }
}

