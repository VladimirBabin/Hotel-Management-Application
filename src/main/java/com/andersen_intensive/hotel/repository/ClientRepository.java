package com.andersen_intensive.hotel.repository;

import com.andersen_intensive.hotel.models.Client;

public interface ClientRepository {

    void addClient(Client client);

    boolean clientExist (Client client);

    void removeClient (Client client);

    void getClientByPhoneNumber(int phoneNumber);

    void getListOfClients();
}
