package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;
import java.util.List;

public interface ClientService {

    Client createClient(String firstName, String lastName, String phoneNumber, int personalID);

    Client getClientByID(int id);

    Client getClientByPhoneNumber(String phoneNumber);

    List<Client> getClientListWithoutSorting();

    List<Client> getClientListSortedByLastName();

    List<Client> getClientListSortedByID();

    Client removeClient(int id);

    boolean isValid(String phoneNumber);
}
