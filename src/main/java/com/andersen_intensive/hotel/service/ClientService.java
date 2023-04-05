package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;

import java.util.Map;

public interface ClientService {

    //create client

    Client createClient(String firstName, String lastName, String phoneNumber);

    //read

    Client getClientByID(int id);

    void getClientList(Map<Integer, Client> clientMap);

    //delete

    void removeClient (int id);

    //update

    void updateClient(Client client);

}
