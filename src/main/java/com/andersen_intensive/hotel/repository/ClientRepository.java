package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;

import java.util.List;

public interface ClientRepository { //CRUD repo Create Read Update Delete
    //подключить драйвер бд

    // create
    Client addClient(Client client);

    // read
    Client getClientById(Integer id);

    List<Client> getAllClients();

    // update
    Client updateClient(Client client);

    // delete
    void deleteClient(int id);
}
