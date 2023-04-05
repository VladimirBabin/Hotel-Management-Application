package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.repository.ApartmentRepositoryImpl;
import com.andersen_intensive.hotel.repository.ClientRepositoryImpl;
import com.andersen_intensive.hotel.service.ClientService;
import com.andersen_intensive.hotel.service.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.andersen_intensive.hotel.models.ApartmentType;

public class ConsoleInteraction {

    private static final String MENU = "Menu:" + "\n" +
            "1. Register a client" + "\n" +
            "2. List of clients" + "\n" +
            "3. Apartment management" + "\n" +
            "4. Service management" + "\n" +
            "5. Check-in" + "\n" +
            "6. Check-out" + "\n" +      //Sv
            "7. Get the current price for client's stay" + "\n" +
            "0. Exit program" + "\n";

    static void showMainMenu() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            ClientService clientService = new ClientServiceImpl(new ClientRepositoryImpl());
            while (true) {
                System.out.println(MENU);
                String input = bufferedReader.readLine();
                switch (input) {
                    case "0":
                        return;
                    case "1":
                        registerNewClient(bufferedReader, clientService);
                        break;
                    case "2":
                        showListOfClients(bufferedReader, clientService);
                        break;
                    case "3":
                        ApartmentManagement.showApartmentManagementMenu(bufferedReader);
                        break;
                    case "4":
                        ServiceManagement.showServiceManagementMenu(bufferedReader);
                        break;
                    case "5":
                        checkIn(bufferedReader);
                        break;
                    case "6":
                        checkOut(bufferedReader);
                        break;
                    case "7":
                        getCurrentPriceForClient(bufferedReader);
                        break;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    //    Mary
    static void registerNewClient(BufferedReader bufferedReader, ClientService clientService) throws IOException {

        System.out.println("Client's name:");
        String name = bufferedReader.readLine();
        System.out.println("Client's last name:");
        String lastName = bufferedReader.readLine();
        System.out.println("Client's phone number:");
        String phoneNumber = bufferedReader.readLine();

        Client clientCreated = clientService.createClient(name, lastName, phoneNumber);
        System.out.println("Client created: " + clientCreated.toString());
    }

    //    Mary
    static void showListOfClients(BufferedReader bufferedReader, ClientService clientService) throws IOException {

        System.out.println("1. List of clients\n" +
                "2. Sort list of clients by last name");

        String input = bufferedReader.readLine();
        List<Client> clients = new ArrayList<>();
                switch (input) {
                    case "1" -> clients = clientService.getClientList(false);
                    case "2" -> clients = clientService.getClientList(true);
                }
        for (Client client: clients) {
            System.out.println("=========================================");
            System.out.println(client.toString());
        }
    }

    //    Vova
    static private void checkIn(BufferedReader bufferedReader) {
    }

    //    Vova
    static private void checkOut(BufferedReader bufferedReader) {
    }

    //    ?
    static private void getCurrentPriceForClient(BufferedReader bufferedReader) {
    }
}
