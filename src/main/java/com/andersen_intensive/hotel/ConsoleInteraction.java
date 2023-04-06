package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.*;
import com.andersen_intensive.hotel.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsoleInteraction {

    private static final ClientServiceImpl clientService = ClientServiceImpl.getInstance();
    private static final ReservationServiceImpl reservationService = ReservationServiceImpl.getInstance();
    private static final ApartmentServiceImpl apartmentService = ApartmentServiceImpl.getInstance();
    private static final ServiceServiceImpl serviceService = ServiceServiceImpl.getInstance();


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


        if (!apartmentService.isValidApartment(number)) {
            while(true) {
                System.out.println("The apartment doesn't exist \n\n");
                System.out.println("To go back type 1");
                String input = bufferedReader.readLine();
                if (input.equals("1")) {
                    return;
                }
            }
        }
        Apartment apartment = apartmentService.getApartmentByNumber(number);

        if (apartmentService.checkIfAvailable(apartment)) {
            apartmentService.update(apartment);
        } else {
            while(true) {
                System.out.println("The apartment is unavailable \n\n");
                System.out.println("To go back type 1");
                String input = bufferedReader.readLine();
                if (input.equals("1")) {
                    return;
                }
            }
        }

        Reservation reservation = new Reservation(client, apartment, LocalDate.now());
        reservationService.addReservation(reservation);

        System.out.println("Information about the reservation:");
        System.out.println(reservation);

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
    static private void checkOut(BufferedReader bufferedReader) throws IOException { //Дата отъезда,  в апартаментах менять статус

//        Ищем бронирование по юзер айди
        int clientId;
        while (true) {
            System.out.println("Client's id:");
            String readLine = bufferedReader.readLine();
            try {
                clientId = Integer.parseInt(readLine);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please type a valid number");
            }
        }
//        if (!reservationService.checkIfOpenReservationExistsForClient(clientId)) {
//            while(true) {
//                System.out.println("There are no unfinished reservations for the user \n\n");
//                System.out.println("To go back type 1");
//                String input = bufferedReader.readLine();
//                if (input.equals("1")) {
//                    return;
//                }
//            }
//        }
        Reservation reservation = reservationService.findByUserId(clientId);
        if (reservation == null) {
            System.out.println("No such user was found");
        } else {
            if(reservationService.checkIfReservationIsOpen(reservation)) {
                reservation.setCheckOut(LocalDate.now());
                reservationService.updateReservation(reservation);
                System.out.println("Information about the reservation:");
                System.out.println(reservation);
                System.out.println("\n\n" +
                        "To go back type 1");
            } else {
                System.out.println("User has no open reservations");
            }
        }
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
