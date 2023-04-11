package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.service.ClientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.andersen_intensive.hotel.ConsoleInteraction.clientService;

public class ClientManagement {

    private static final String CLIENT_MANAGEMENT_MENU = """ 
            1. Create a new client
            2. Show list of clients
            3. Update client's information
            4. Delete client from base
            To go back type 0
            """;

    static void showClientManagementMenu(BufferedReader bufferedReader, ClientService clientService) throws IOException {
        while (true) {
            while (true) {
                System.out.println(CLIENT_MANAGEMENT_MENU);
                String input = bufferedReader.readLine();
                switch (input) {
                    case "0":
                        return;
                    case "1":
                        createClient(bufferedReader, clientService);
                        break;
                    case "2":
                        showListOfClients(bufferedReader);
                        break;
                    case "3":
                        updateClientsInformation(bufferedReader, clientService);
                        break;
                    case "4":
                        removeClient(bufferedReader, clientService);
                        break;
                }
            }
        }
    }

    static void createClient(BufferedReader bufferedReader, ClientService clientService) throws IOException {

        System.out.println("Client's name:");
        String name = bufferedReader.readLine();
        System.out.println("Client's last name:");
        String lastName = bufferedReader.readLine();
        System.out.println("Client's phone number:");
        String phoneNumber = bufferedReader.readLine();
        Client clientCreated = clientService.createClient(name, lastName, phoneNumber);
        System.out.println("Client created: " + "\n" + clientCreated.toString() + "\n");
    }

    static void showListOfClients(BufferedReader bufferedReader) throws IOException {

        System.out.println("\n1. List of clients\n" +
                "2. Sort list of clients by last name\n" +
                "3. Sort list of clients by ID\n");

        String input = bufferedReader.readLine();
        List<Client> clients = new ArrayList<>();
        switch (input) {
            case "1" -> clients = clientService.getClientListWithoutSorting();
            case "2" -> clients = clientService.getClientListSortedByLastName();
            case "3" -> clients = clientService.getClientListSortedByID();
        }
        if (clients.isEmpty()) {
            System.out.println("Client list is empty!");
        } else {
            for (Client client : clients) {
                System.out.println("=========================================");
                System.out.println(client.toString());
            }
        }

        System.out.println("\n\n" +
                "To go back type 0");
        while (true) {
            input = bufferedReader.readLine();
            if (input.equals("0")) {
                return;
            }
        }
    }

    static void removeClient(BufferedReader bufferedReader, ClientService clientService) throws IOException {

        System.out.println("Write client's id:");
        int clientID;
        while (true) {
            try {
                clientID = Integer.parseInt(bufferedReader.readLine());
                if (clientID <= 0) {
                    System.out.println("Client's ID must be greater than 0. Please try again.");
                } else {
                    System.out.println("Client " + clientService.getClientByID(clientID).getLastName() + " " +
                            clientService.getClientByID(clientID).getFirstName() + " successfully removed from base!");
                    clientService.removeClient(clientID);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid ID number.");
            }
            String input = bufferedReader.readLine();
            System.out.println("\n\n" +
                    "To go back type 0");
            while (true) {
                if (input.equals("0")) {
                    return;
                }
            }
        }
    }

    static void updateClientsInformation(BufferedReader bufferedReader, ClientService clientService) throws IOException {

        System.out.println("Write client's id:");
        int clientID;
        while (true) {
            try {
                clientID = Integer.parseInt(bufferedReader.readLine());
                if (clientID <= 0) {
                    System.out.println("Client's ID must be greater than 0. Please try again.");
                } else {
                    System.out.println("Client's name:");
                    String newName = bufferedReader.readLine();
                    System.out.println("Client's last name:");
                    String newLastName = bufferedReader.readLine();
                    System.out.println("Client's phone number:");
                    String newPhoneNumber = bufferedReader.readLine();

                    clientService.getClientByID(clientID).setFirstName(newName);
                    clientService.getClientByID(clientID).setLastName(newLastName);
                    clientService.getClientByID(clientID).setPhoneNumber(newPhoneNumber);

                    System.out.println("Client's information was successfully updated!" + "\n" +
                            clientService.getClientByID(clientID).toString() + "\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid ID number.");
            }
            String input = bufferedReader.readLine();
            System.out.println("\n\n" +
                    "To go back type 0");
            while (true) {
                if (input.equals("0")) {
                    return;
                }
            }
        }
    }
}
