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

    private static final String CLIENT_MANAGEMENT_MENU = "1. Create a new client" + "\n" +
            "2. Show list of clients" + "\n" +
            "3. Update client's information" + "\n" +
            "4. Delete client from base" + "\n" + "\n" +
            "To go back type 0\n";

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
        System.out.println("Client created: " + "\n" + clientCreated.toString());
    }

    static void showListOfClients(BufferedReader bufferedReader) throws IOException {

        System.out.println("\n1. List of clients\n" +
                "2. Sort list of clients by last name\n");

        String input = bufferedReader.readLine();
        List<Client> clients = new ArrayList<>();
        switch (input) {
            case "1" -> clients = clientService.getClientList(false);
            case "2" -> clients = clientService.getClientList(true);
        }
        for (Client client : clients) {
            System.out.println("=========================================");
            System.out.println(client.toString());
        }

        System.out.println("\n\n" +
                "0. Go back");
        while (true) {
            input = bufferedReader.readLine();
            if (input.equals("1")) {
                return;
            }
        }
    }

    static void removeClient(BufferedReader bufferedReader, ClientService clientService) throws IOException {

        int clientID;

        while (true) {
            System.out.println("Write client's id:");
            String readLine = bufferedReader.readLine();
            try {
                clientID = Integer.parseInt(readLine);
                System.out.println("Client " + clientService.getClientByID(clientID).getLastName() + " " +
                        clientService.getClientByID(clientID).getFirstName() + " successfully removed from base!");
                clientService.removeClient(clientID);
            } catch (NumberFormatException e) {
                System.out.println("Please type a valid number");
            }
        }
    }

    static void updateClientsInformation(BufferedReader bufferedReader, ClientService clientService) throws IOException {

        int clientID;

        while (true) {
            System.out.println("\nWrite client's id:");
            String readLine = bufferedReader.readLine();
            try {
                clientID = Integer.parseInt(readLine);
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

            } catch (NumberFormatException e) {
                System.out.println("Please type a valid number");
            }
        }
    }
}
