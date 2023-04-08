package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;

import java.util.*;


public class ClientRepositoryImpl implements ClientRepository {

    private final Map<UUID, Client> clients = new HashMap<>();

    @Override
    public Client addClient(Client client) {
        clients.put(client.getPersonalID(), client);
        return client;
    }

    @Override
    public Client getClientById(UUID id) {
        return clients.get(id);
    }

    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(clients.values());
    }

    @Override
    public Client updateClient(Client client) {
        UUID id = client.getPersonalID();
        clients.put(id, client);
        Client updatedClient = clients.get(id);
        return updatedClient;
    }

    @Override
    public void deleteClient(UUID id) {
        clients.remove(id);
    }
}