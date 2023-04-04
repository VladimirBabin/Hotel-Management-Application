package com.andersen_intensive.hotel.repository;

import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {
    List<Client> clients;

    @Override
    public void addClient(Client client) {
        if (client == null) {
            clients = new ArrayList<Client>();
        }
        clients.add(client);
    }

    @Override
    public Client getClientByPhoneNumber(int phoneNumber) {
        return null;
    }

    @Override
    public List<Client> getListOfClients() {
        return null;
    }
}
