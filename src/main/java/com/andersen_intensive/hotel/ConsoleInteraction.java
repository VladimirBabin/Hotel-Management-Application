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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class ConsoleInteraction {

    static final ClientService clientService = new ClientServiceImpl(new ClientRepositoryImpl());
    static final ReservationService reservationService = new ReservationServiceImpl(new ReservationRepositoryImpl());
    static final ApartmentService apartmentService = new ApartmentServiceImpl(new ApartmentRepositoryImpl());
    static final UtilityService utilityService = new UtilityServiceImpl(new UtilityRepositoryImpl());

    private static final String MENU = "Menu:" + "\n" +
            "1. Client management" + "\n" +
            "2. Apartment management" + "\n" +
            "3. Utility management" + "\n" +
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
                        UtilityManagement.showUtilityManagementMenu(bufferedReader, utilityService, reservationService);
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

    static private void checkIn(BufferedReader bufferedReader) throws IOException {
        int id;
        int number;
        int reservationId;

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
            apartmentService.setStatus(number,ApartmentStatus.OCCUPIED);
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

        while (true) {
            System.out.println("Number of Reservation:");
            String reservationNumber = bufferedReader.readLine();
            try {
                reservationId = Integer.parseInt(reservationNumber);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please type a valid number");
            }
        }

        Reservation reservation = new Reservation(reservationId, client, apartment, LocalDate.now());
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

    static private void checkOut(BufferedReader bufferedReader) throws IOException {

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

        Reservation reservation = reservationService.getReservationByID(clientId);
        if (reservation == null) {
            System.out.println("No such user was found");
        } else {
            if (reservationService.checkIfReservationIsOpen(reservation)) {
                LocalDate checkOutDate;
                while (true) {
                    System.out.println("Check-out date (yy/MM/dd):");
                    String checkOutStr = bufferedReader.readLine().trim().replace("-", "/").replace(".", "/"); // replace other separators with '/'
                    try {
                        checkOutDate = LocalDate.parse(checkOutStr, DateTimeFormatter.ofPattern("yy/MM/dd"));
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format, please try again.");
                    }
                }
                reservation.setCheckOut(checkOutDate);
                reservationService.updateReservation(reservation);
                Apartment apartment = reservation.getApartment();
                apartment.setApartmentStatus(ApartmentStatus.AVAILABLE);
                apartmentService.update(apartment);
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

    static private void getCurrentPriceForClient(BufferedReader bufferedReader) throws IOException {
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

        Reservation reservation = reservationService.getReservationByID(clientId);
        long daysBetween;
        if (reservation == null) {
            System.out.println("No such user was found");
            return;
        } else {
            if (reservationService.checkIfReservationIsOpen(reservation)) {
                System.out.println("User need to set check uot date");
                return;
            } else {
                daysBetween = ChronoUnit.DAYS.between(reservation.getCheckIn(), reservation.getCheckOut());
            }
        }
        BigDecimal summary = new BigDecimal(daysBetween).multiply(reservation.getApartment().getApartmentPrice());
        for (Utility utility: reservation.getUtilities()) {
            summary = summary.add(utility.getPrice());
        }
        System.out.println("Current price for client " + clientId + " = " + summary);
    }
}
