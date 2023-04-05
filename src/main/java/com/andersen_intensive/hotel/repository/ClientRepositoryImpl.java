package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

// аннотация репозитория

@Slf4j
public class ClientRepositoryImpl implements ClientRepository {

    private final Map<Integer, Client> clients = new HashMap<>();

    @Override
    public Client addClient(Client client) {
        clients.put(client.getPersonalID(), client);
        return client;
    }

    @Override
    public Client getClientById(Integer id) {
        return clients.get(id);
    }

    @Override
    public List<Client> getAllClients() {

    //    for (Map.Entry<Integer, Client> entry : clients.entrySet()) {
    //        System.out.println(entry.getKey() + " : " + entry.getValue().getFirstName() + " " + entry.getValue().getLastName() +
    //    " " + entry.getValue().getPhoneNumber());
    //    }

        return new ArrayList<>(clients.values());
    }

    @Override
    public Client updateClient(Client client) {

    //    for (int i = 0; i < clients.size(); i++) {
    //        if (clients.get(i).getPersonalID() == client.getPersonalID()) {
    //            clients.put(clients.get(i).getPersonalID(), client);
    //            break;
    //        }
    //    }

        Integer id = client.getPersonalID();
        clients.put(id, client);
        Client updatedClient = clients.get(id);
        return updatedClient;
    }

    @Override
    public void deleteClient(int id) {
        clients.remove(id);
    }
}