package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.repository.ClientRepository;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(String firstName, String lastName, String phoneNumber) {
        Client client = new Client(firstName, lastName, phoneNumber);
        return clientRepository.addClient(client);
    }

    @Override
    public Client getClientByID(int id) {
        return clientRepository.getClientById(id);
    }

    @Override
    public List<Client> getClientList(boolean sortByLastName) {
        List<Client> clients = clientRepository.getAllClients();
        if (sortByLastName) {
            clients.sort(Comparator.comparing(Client::getLastName));
        }
        return clients;
    }

    @Override
    public void removeClient(int id) {
        clientRepository.deleteClient(id);
    }

    @Override
    public Client updateClient(Client clientToUpdate) {
        Client updatedClient = clientRepository.updateClient(clientToUpdate);
        return updatedClient;
    }

    public Map<Integer, Client> alphabeticalSorting(Map<Integer, Client> clients) {
        Map<Integer, Client> sortedClients = new LinkedHashMap<>();
        clients.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getLastName() != null)
                .filter(entry -> entry.getValue().getFirstName() != null)
                .sorted(Comparator.comparing(entry -> entry.getValue().getLastName()))
                .forEach(entry -> sortedClients.put(entry.getKey(), entry.getValue()));

        return sortedClients;
    }


}