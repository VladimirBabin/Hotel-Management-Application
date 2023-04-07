package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.repository.ClientRepository;
import com.andersen_intensive.hotel.repository.ClientRepositoryImpl;
import com.andersen_intensive.hotel.repository.ServiceRepositoryImpl;

import java.util.Comparator;
import java.util.List;

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
    public List<Client> getClientList(boolean sortByLastName) {
        List<Client> clients = clientRepository.getAllClients();
        if (sortByLastName) {
            clients.sort(Comparator.comparing(Client::getLastName));
        }
        return clients;
    }

    @Override
    public void removeClient(int id) {
        clientRepository.deleteClient(id);
    }

    @Override
    public Client updateClient(Client clientToUpdate) {
        Client updatedClient = clientRepository.updateClient(clientToUpdate);
        return updatedClient;
    }
}