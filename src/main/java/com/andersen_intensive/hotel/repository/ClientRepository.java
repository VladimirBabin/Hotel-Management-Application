package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;

import java.util.List;

public interface ClientRepository {

    void addClient(Client client);

    Client getClientByPhoneNumber(int phoneNumber);

    List<Client> getListOfClients();
}
