package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

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
        Client clientFromMemory = clientRepository.findByPhoneNumber(phoneNumber);
        if (clientFromMemory == null) {
            throw new EntityNotFoundException("Entity with this phone number does not exist");
        }
        return clientFromMemory;
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

        Client client = clientFromMemory.get();
        client.setPhoneNumber(newPhoneNumber);
        clientRepository.save(client);

        if (clientFromMemory.isEmpty()) {
            throw new EntityNotFoundException("Client with this id does not exist");
        }

        return client;
    }
}