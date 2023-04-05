package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.repository.ClientRepository;

import java.util.Map;


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

    @Override
    public Client getClientByID(int id) {
        return clientRepository.getClientById(id);
    }

    @Override
    public void getClientList(Map<Integer, Client> clientMap) {
        clientRepository.getAllClients();
    }

    @Override
    public void removeClient(int id) {
        clientRepository.deleteClient(id);
    }

    @Override
    public void updateClient(Client client) {
        clientRepository.updateClient(client);
    }
}
