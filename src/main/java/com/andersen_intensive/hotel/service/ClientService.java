package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;

import java.util.List;
import java.util.Map;

public interface ClientService {

    //create client

    Client createClient(String firstName, String lastName, String phoneNumber);

    //read

    Client getClientByID(int id);

    List<Client> getClientList(boolean sortByLastName);

    //delete

    void removeClient(int id);

    //update

    Client updateClient(Client client);

}
