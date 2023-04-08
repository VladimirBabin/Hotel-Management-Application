package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    //create client
    Client createClient(String firstName, String lastName, String phoneNumber);

    //read
    Client getClientByID(UUID id);

    List<Client> getClientList(boolean sortByLastName);

    //delete
    void removeClient(UUID id);

    //update
    Client updateClient(Client client);

}
