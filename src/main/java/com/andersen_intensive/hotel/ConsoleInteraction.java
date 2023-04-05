package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.*;
import com.andersen_intensive.hotel.repository.ApartmentRepositoryImpl;
import com.andersen_intensive.hotel.repository.ClientRepositoryImpl;
import com.andersen_intensive.hotel.repository.ReservationRepositoryImpl;
import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.service.ClientService;
import com.andersen_intensive.hotel.service.ClientServiceImpl;
import com.andersen_intensive.hotel.service.ReservationServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ConsoleInteraction {

    static final ClientService clientService = new ClientServiceImpl();
//    не хватает реализации ApartmentService
    private static ApartmentService apartmentService;

    private static ReservationServiceImpl reservationService;


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
//            ClientService clientService = new ClientServiceImpl(new ClientRepositoryImpl());
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

        while (true) {
            System.out.println("Client created: " + clientCreated.toString() + "\n\n" +
                    "To go back type 1");
            String input = bufferedReader.readLine();
            switch (input) {
                case "1" -> {return;}
            }
        }
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

        System.out.println("\n\n" +
                "To go back type 1");
        while (true) {
            input = bufferedReader.readLine();
            if (input.equals("1")) {
                return;
            }
        }
    }

    //    Vova
    static private void checkIn(BufferedReader bufferedReader) throws IOException {
        int id;
        int number;
        while (true) {
            System.out.println("Client's id:");
            String clientId = bufferedReader.readLine();
            try {
                id = Integer.parseInt(clientId);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please type a valid number");
            }
        }

        while (true) {
            System.out.println("Number of Apartment:");
            String apartmentNumber = bufferedReader.readLine();
            try {
                number = Integer.parseInt(apartmentNumber);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please type a valid number");
            }
        }

        Client client = clientService.getClientByID(id);
        Apartment apartment = apartmentService.getApartmentByNumber(number);
        apartment.setApartmentStatus(ApartmentStatus.OCCUPIED);
        apartmentService.update(apartment);
        Reservation reservation = new Reservation(client, apartment, LocalDate.now());
        reservationService.updateReservation(reservation);

        System.out.println("Information about the reservation:");

        System.out.println("\n\n" +
                "To go back type 1");
        while (true) {
            String input = bufferedReader.readLine();
            if (input.equals("1")) {
                return;
            }
        }
    }

    //    Vova
    static private void checkOut(BufferedReader bufferedReader) throws IOException {

//        Ищем бронирование по юзер айди
        int id;
        while (true) {
            System.out.println("Client's id:");
            String clientId = bufferedReader.readLine();
            try {
                id = Integer.parseInt(clientId);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please type a valid number");
            }
        }
        Reservation reservation = reservationService.findByUserId(id);

        reservation.setCheckOut(LocalDate.now());
        reservationService.updateReservation(reservation);
        System.out.println("Information about the reservation:");

        System.out.println("\n\n" +
                "To go back type 1");
        while (true) {
            String input = bufferedReader.readLine();
            if (input.equals("1")) {
                return;
            }
        }
    }

    //    ?
    static private void getCurrentPriceForClient(BufferedReader bufferedReader) {
    }
}
