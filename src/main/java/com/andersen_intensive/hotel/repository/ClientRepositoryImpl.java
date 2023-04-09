package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClientRepositoryImpl implements ClientRepository {

    private final Map<Integer, Client> clients = new HashMap<>();
    private static int count = 0;

    @Override
    public Client addClient(Client client) {
        count++;
        client.setPersonalID(count);
        clients.put(client.getPersonalID(), client);
        return client;
    }

    @Override
    public Client getClientById(int id) {
        return clients.get(id);
    }

    @Override
    public List<Client> getAllClients() {
        return new ArrayList<>(clients.values());
    }

    @Override
    public Client updateClient(Client client) {
        int id = client.getPersonalID();
        clients.put(id, client);
        return clients.get(id);
    }

    @Override
    public void deleteClient(int id) {
        clients.remove(id);
    }
}