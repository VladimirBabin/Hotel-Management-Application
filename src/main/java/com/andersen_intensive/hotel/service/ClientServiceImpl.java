package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.repository.ClientRepository;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(String firstName, String lastName, String phoneNumber) {
        Client client = new Client(firstName, lastName, phoneNumber);
        return clientRepository.addClient(client);
    }
}
