package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {

    private List<Client> clients;

    public void ClientList() {
        if (clients == null) {
            clients = new ArrayList<>();
        }
    }

    @Override
    public void addClient(Client client) {
        clients.add(client);
    }

    @Override
    public boolean clientExist(Client client) {
        for (Client c : clients) {
            if (c.getPhoneNumber() == client.getPhoneNumber()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeClient(Client client) {
        clients.remove(client);
    }

    @Override
    public void getClientByPhoneNumber(int phoneNumber) {
        for (Client client : clients) {
            if (client.getPhoneNumber() == (phoneNumber)) {
                System.out.println(client.getFirstName() + " " + client.getLastName());;
            } else {
                System.out.println("No result");
            }
        }
    }

    @Override
    public void getListOfClients() {
        if (clients.size() > 0) {
            System.out.println("List of clients:");
            for (Client client : clients) {
                System.out.println(client.getPersonalID() + " 1. " + client.getFirstName() + " " + client.getLastName() +
                        " - " + client.getPhoneNumber());
            }
        } else {
            System.out.println("No clients in system!");
        }
    }
}