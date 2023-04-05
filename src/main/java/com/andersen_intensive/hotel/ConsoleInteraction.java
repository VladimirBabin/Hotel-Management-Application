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

    private final String MENU = "Menu:" + "\n" +
            "1. Register a client" + "\n" +
            "2. List of available apartments" + "\n" +
            "3. Reservations" + "\n" +
            "4. Price management" + "\n" +
            "5. Show info about current reservation" + "\n" +
            "6. Add new apartment" + "\n"+      //Sv
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
                    case "6":           //Sv
                        addApartment(bufferedReader);
                        break;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    private static void addApartment(BufferedReader bufferedReader) throws IOException{     //Sv

        System.out.println("Enter apartment number:");
        int apartmentNumber = Integer.parseInt(bufferedReader.readLine());

        System.out.println("Enter price per night:");
        double apartmentPrice = enterApartmentPrice(bufferedReader);

        System.out.println("Enter room type: 1 for single bed, 2 for double bed:");
        ApartmentType apartmentType = enterApartmentType(bufferedReader);

        Apartment apartment = new Apartment(apartmentNumber, apartmentPrice, apartmentType);

//здесь нужно добавить апартаменты в общий Лист

        System.out.println("Apartment added successfully!");


    }

    private static ApartmentType enterApartmentType(BufferedReader bufferedReader) {        //Sv
        try {
            return ApartmentType.valueOfLabel(bufferedReader.readLine());
        } catch (IllegalArgumentException | IOException exp) {
            System.out.println("Invalid apartment type! Please, choose 1 for single bed or 2 for double bed:");
            return enterApartmentType(bufferedReader);
        }
    }

    private static double enterApartmentPrice(BufferedReader bufferedReader) {      //Sv
        try {
            return Double.parseDouble(bufferedReader.readLine());
        } catch (NumberFormatException | IOException exp) {
            System.out.println("Invalid apartment price! Please, enter a valid double number. " +
                    "Decimals should be separated by point (.)");
            return enterApartmentPrice(bufferedReader);
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
