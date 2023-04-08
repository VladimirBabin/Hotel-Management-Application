package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;

import java.util.List;
import java.util.UUID;

public interface ClientRepository { //CRUD repo Create Read Update Delete
    //подключить драйвер бд

    // create
    Client addClient(Client client);

    // read
    Client getClientById(UUID id);

    List<Client> getAllClients();

    // update
    Client updateClient(Client client);

    // delete
    void deleteClient(UUID id);
}
