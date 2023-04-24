package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client saveClient(Client client) {
        clientRepository.save(client);
        return client;
    }

    public List<Client> showAll() {
        return clientRepository.findAll();
    }

    public Client findClientById(Long id) {
        Optional<Client> clientFromMemory = clientRepository.findById(id);
        if (clientFromMemory.isEmpty()) {
            throw new EntityNotFoundException("Entity with this id does not exist");
        }
        return clientFromMemory.get();
    }

    public Client findClientByPhoneNumber(String phoneNumber) {
        Optional<Client> clientFromMemory = Optional.ofNullable(clientRepository.findByPhoneNumber(phoneNumber));
        if (clientFromMemory.isEmpty()) {
            throw new EntityNotFoundException("Entity with this phone number does not exist");
        }
        return clientFromMemory.get();
    }

    public List<Client> sortByLastName() {
        List<Client> sortedClients = clientRepository.findAll();
        sortedClients.sort((s1, s2) -> s1.getLastName().compareToIgnoreCase(s2.getLastName()));
        return sortedClients;
    }

    public List<Client> sortClientsByID() {
        List<Client> sortedClients = clientRepository.findAll();
        sortedClients.sort(Comparator.comparing(Client::getId));
        return sortedClients;
    }

    public Client changePhoneNumber(Long id, String newPhoneNumber) {
        Optional<Client> clientFromMemory = clientRepository.findById(id);

        if (clientFromMemory.isEmpty()) {
            throw new EntityNotFoundException("Utility with this phone number does not exist");
        }

        Client client = clientFromMemory.get();
        client.setPhoneNumber(newPhoneNumber);
        clientRepository.update(client);
        return client;
    }
}