package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;

import java.util.List;

public interface ClientRepository {

    Client addClient(Client client);

    Client getClientById(int id);

    List<Client> getAllClients();

    void updateClient(Client client);

    void deleteClient(int id);

}
