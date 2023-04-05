package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;

public interface ClientService {
    //create client

    Client createClient(String firstName, String lastName, String phoneNumber);

}
