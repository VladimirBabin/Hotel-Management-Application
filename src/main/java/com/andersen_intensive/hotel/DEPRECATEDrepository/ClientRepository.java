package com.andersen_intensive.hotel.DEPRECATEDrepository;

import com.andersen_intensive.hotel.models.Client;
import java.util.List;

public interface ClientRepository {

    Client addClient(Client client);

    Client getClientById(int id);

    Client getClientByPhoneNumber(String phoneNumber);

    List<Client> getAllClients();

    void updateClient(Client client);

    void deleteClient(int id);
}
