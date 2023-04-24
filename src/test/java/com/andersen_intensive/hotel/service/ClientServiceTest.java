package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ClientServiceTest {

    private final ClientRepository clientRepository = mock(ClientRepository.class);
    private final ClientService clientService = new ClientService(clientRepository);

    @Test
    void saveClientTest() {
        Client client = new Client("Max", "Maxon", "89112233444");

        when(clientRepository.findByPhoneNumber(client.getPhoneNumber())).thenReturn(null);

        Client savedClient = clientService.saveClient(client);

        verify(clientRepository, times(1)).
                findByPhoneNumber(client.getPhoneNumber());
        verify(clientRepository, times(1)).save(client);
        assertNotNull(savedClient);
    }

    @Test
    void showAllTest() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Max", "Maxon", "89112233444"));
        clients.add(new Client("Xam", "Noxam", "89113344555"));
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.showAll();
        assertEquals(clients, result);
    }

    @Test
    public void FindClientByIdSuccessTest() {
        Client client = new Client("Max", "Maxon", "89112233444");
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        Client result = clientService.findClientById(client.getId());
        assertEquals(client, result);
    }

    @Test
    public void FindClientByIdNotFoundTest() {
        Long id = 1L;
        when(clientRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> clientService.findClientById(id));
    }

    @Test
    void findClientByPhoneNumberSuccessTest() {
        Client client = new Client("Max", "Maxon", "89112233444");
        when(clientRepository.findByPhoneNumber(client.getPhoneNumber())).thenReturn(client);

        Client result = clientService.findClientByPhoneNumber(client.getPhoneNumber());
        assertEquals(client, result);
    }

    @Test
    public void findClientByPhoneNumberNotFoundTest() {
        String phoneNumber = "89112324939";
        when(clientRepository.findByPhoneNumber(phoneNumber)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
                clientService.findClientByPhoneNumber(phoneNumber));
    }

    @Test
    void sortByLastNameTest() {
        List<Client> clients = Arrays.asList(
                new Client("Max", "Maxon", "89112233444"),
                new Client("Xam", "Noxam", "89113344555"),
                new Client("Aron", "Nora", "89114455666")
        );
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> sortedClients = clientService.sortByLastName();

        assertEquals("Maxon", sortedClients.get(0).getLastName());
        assertEquals("Nora", sortedClients.get(1).getLastName());
        assertEquals("Noxam", sortedClients.get(2).getLastName());
    }

    @Test
    void sortClientsByIDTest() {
        List<Client> clients = Arrays.asList(
                new Client("Max", "Maxon", "89112233444"),
                new Client("Xam", "Noxam", "89113344555"),
                new Client("Aron", "Nora", "89114455666")
        );
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> sortedClients = clientService.sortByLastName();

        assertEquals("Maxon", sortedClients.get(0).getLastName());
        assertEquals("Nora", sortedClients.get(1).getLastName());
        assertEquals("Noxam", sortedClients.get(2).getLastName());
    }

    @Test
    void changePhoneNumber() {
        Client client = new Client("Max", "Maxon", "89112233444");
        when(clientRepository.findByPhoneNumber(client.getPhoneNumber())).thenReturn(client);

        Client clientUpdated = clientService.changePhoneNumber(client.getId(),
                "89111122333");
        assertEquals("89111122333", clientUpdated.getPhoneNumber());
    }
}