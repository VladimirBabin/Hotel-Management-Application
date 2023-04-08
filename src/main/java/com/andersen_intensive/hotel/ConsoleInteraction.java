package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.*;
import com.andersen_intensive.hotel.repository.ApartmentRepositoryImpl;
import com.andersen_intensive.hotel.repository.ClientRepositoryImpl;
import com.andersen_intensive.hotel.repository.ReservationRepositoryImpl;
import com.andersen_intensive.hotel.repository.UtilityRepositoryImpl;
import com.andersen_intensive.hotel.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConsoleInteraction {

    static final ClientService clientService = new ClientServiceImpl(new ClientRepositoryImpl());
    static final ReservationService reservationService = new ReservationServiceImpl(new ReservationRepositoryImpl());
    static final ApartmentService apartmentService = new ApartmentServiceImpl(new ApartmentRepositoryImpl());
    static final UtilityService utilityService = new UtilityServiceImpl(new UtilityRepositoryImpl());

    private static final String MENU = "Menu:" + "\n" +
            "1. Client management" + "\n" +
            "2. Apartment management" + "\n" +
            "3. Service management" + "\n" +
            "4. Check-in" + "\n" +
            "5. Check-out" + "\n" +
            "6. Get the current price for client's stay" + "\n" +
            "0. Exit program" + "\n";

    static void showMainMenu() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                System.out.println(MENU);
                String input = bufferedReader.readLine();
                switch (input) {
                    case "0":
                        return;
                    case "1":
                        ClientManagement.showClientManagementMenu(bufferedReader, clientService);
                        break;
                    case "2":
                        ApartmentManagement.showApartmentManagementMenu(bufferedReader, apartmentService);
                        break;
                    case "3":
                        UtilityManagement.showUtilityManagementMenu(bufferedReader, utilityService);
                        break;
                    case "4":
                        checkIn(bufferedReader);
                        break;
                    case "5":
                        checkOut(bufferedReader);
                        break;
                    case "6":
                        getCurrentPriceForClient(bufferedReader);
                        break;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    //    Vova
    static private void checkIn(BufferedReader bufferedReader) throws IOException {
        UUID id;
        int number;

        while (true) {
            System.out.println("Client's id:");
            String clientId = bufferedReader.readLine();
            try {
                id = UUID.fromString(clientId);
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


        if (!apartmentService.isValid(number)) {
            while (true) {
                System.out.println("The apartment doesn't exist \n\n");
                System.out.println("To go back type 1");
                String input = bufferedReader.readLine();
                if (input.equals("1")) {
                    return;
                }
            }
        }
        Apartment apartment = apartmentService.getById(number);

        if (apartmentService.checkIfAvailable(apartment)) {
            apartmentService.update(apartment);
        } else {
            while (true) {
                System.out.println("The apartment is unavailable \n\n");
                System.out.println("To go back type 1");
                String input = bufferedReader.readLine();
                if (input.equals("1")) {
                    return;
                }
            }
        }

        Reservation reservation = new Reservation(client, apartment, LocalDate.now());
        reservationService.createReservation(reservation);

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
        UUID clientId;
        while (true) {
            System.out.println("Client's id:");
            String readLine = bufferedReader.readLine();
            // wrapping with try catch in case the user enters a different from number value
            try {
                clientId = UUID.fromString(readLine);
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
        Reservation reservation = reservationService.getReservationByID(clientId);
        if (reservation == null) {
            System.out.println("No such user was found");
        } else {
            if (reservationService.checkIfReservationIsOpen(reservation)) {
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
