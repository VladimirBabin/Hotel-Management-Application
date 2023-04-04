package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.service.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInteraction {

    private final String MENU = "Menu:" + "\n" +
            "1. Register a client" + "\n" +
            "2. List of available apartments" + "\n" +
            "3. Reservations" + "\n" +
            "4. Price management" + "\n" +
            "5. Show info about current reservation" + "\n" +
            "0. Exit program" + "\n";
    private ClientServiceImpl clientService;

    void mainMenuStart() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println(MENU);
                String input = bufferedReader.readLine();
                switch (input) {
                    case "0":
                        return;
                    case "1":
                        break;
                    case "2":
                        showListOfAvailableApartments(bufferedReader);
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    case "5":
                        break;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    void registerNewClient(BufferedReader bufferedReader) throws IOException {
        System.out.println("Client's name:");
        String name = bufferedReader.readLine();
        System.out.println("Clients last name:");
        String lastName = bufferedReader.readLine();
        System.out.println("Client's phone number:");
        String phoneNumber = bufferedReader.readLine();

        Client client = new Client(name, lastName, phoneNumber);
        clientService.save();
    }

    void showListOfAvailableApartments(BufferedReader bufferedReader) throws IOException {
        System.out.println("List of available apartments" + "\n" + "\n" +
                "To go back type 1");
        while (true) {
            String input = bufferedReader.readLine();
            if (input.equals("1")) {
                break;
            }
        }
    }

    void startReservation() {

    }

    void priceManagement() {

    }

    void showInformationOnReservation() {

    }

}
