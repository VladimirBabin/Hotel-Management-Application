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
    public Client getClientById(int id) {
        for (Map.Entry<Integer, Client> entry : clients.entrySet()) {
            if (entry.getKey().equals(id)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void getAllClients() {
        for (Map.Entry<Integer, Client> entry : clients.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().getFirstName() + " " + entry.getValue().getLastName() +
                    " " + entry.getValue().getPhoneNumber());
        }
    }

    @Override
    public void updateClient(Client client) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getPersonalID() == client.getPersonalID()) {
                clients.put(clients.get(i).getPersonalID(), client);
                break;
            }
        }
    }

    @Override
    public void deleteClient(int id) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getPersonalID() == id) {
                clients.remove(i);
                break;
            }
        }
    }

    public Map<Integer, Client> alphabeticalSorting(Map<Integer, Client> clients) {
        Map<Integer, Client> sortedClients = new LinkedHashMap<>();
        clients.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getLastName() != null)
                .filter(entry -> entry.getValue().getFirstName() != null)
                .sorted(Comparator.comparing(entry -> entry.getValue().getLastName()))
                .forEach(entry -> sortedClients.put(entry.getKey(), entry.getValue()));

        return sortedClients;
    }
}