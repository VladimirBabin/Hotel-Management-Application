package com.andersen_intensive.hotel.repository;

import java.util.List;

public interface ClientRepository {

    void addClient(Client client);

    Client getClientByPhoneNumber(int phoneNumber);

    List<Client> getListOfClients();
}
