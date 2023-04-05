package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;

import java.util.Map;

public interface ClientRepository { //CRUD repo Create Read Update Delete
    //подключить драйвер бд

    // create
    Client addClient(Client client);

    // read
    Client getClientById(int id);

    void getAllClients();

    // update
    void updateClient(Client client);

    // delete
    void deleteClient(int id);
}
