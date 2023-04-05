package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.repository.ApartmentRepositoryImpl;
import com.andersen_intensive.hotel.service.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Scanner;
import com.andersen_intensive.hotel.models.ApartmentType;
public class ConsoleInteraction {

    private static final String MENU = "Menu:" + "\n" +
            "1. Register a client" + "\n" +
            "2. List of clients" + "\n" +
            "3. Apartment management" + "\n" +
            "4. Service management" + "\n" +
            "5. Check-in" + "\n" +
            "6. Check-out" + "\n"+      //Sv
            "7. Get the current price for client's stay" + "\n" +
            "0. Exit program" + "\n";

    private ClientServiceImpl clientService;

    static void showMainMenu() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println(MENU);
                String input = bufferedReader.readLine();
                switch (input) {
                    case "0":
                        return;
                    case "1":
                        registerNewClient(bufferedReader);
                        break;
                    case "2":
                        showListOfClients(bufferedReader);
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
    static void registerNewClient(BufferedReader bufferedReader) throws IOException {
        System.out.println("Client's name:");
        String name = bufferedReader.readLine();
        System.out.println("Clients last name:");
        String lastName = bufferedReader.readLine();
        System.out.println("Client's phone number:");
        String phoneNumber = bufferedReader.readLine();

//        Client client = new Client(name, lastName, phoneNumber);
//        clientService.save();
    }

//    Mary
    static void showListOfClients(BufferedReader bufferedReader) throws IOException {

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
