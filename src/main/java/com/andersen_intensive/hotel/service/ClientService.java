package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;
import java.util.List;

public interface ClientService {

    Client createClient(String firstName, String lastName, String phoneNumber);

    Client getClientByID(int id);

    List<Client> getClientListWithoutSorting();

    List<Client> getClientListSortedByLastName();

    List<Client> getClientListSortedByID();

    void removeClient(int id);
}
