package com.andersen_intensive.hotel.management;

import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.models.Utility;
import com.andersen_intensive.hotel.service.ReservationService;
import com.andersen_intensive.hotel.service.UtilityService;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

public class UtilityManagement {

    private static final String UTILITY_MANAGEMENT_MENU = "1. Add service" + "\n" +
            "2. Apply a service for client" + "\n" +
            "3. Show list of services" + "\n" +
            "4. Change service price" + "\n" + "\n" +
            "To go back type 0";

    public static void showUtilityManagementMenu(BufferedReader bufferedReader,
                                                 UtilityService utilityService,
                                                 ReservationService reservationService)
            throws IOException {
        System.out.println(UTILITY_MANAGEMENT_MENU);
        while (true) {
            String input = bufferedReader.readLine();
            switch (input) {
                case "0" -> {
                    return;
                }
                case "1" -> addUtility(bufferedReader, utilityService);
                case "2" -> applyUtilityForReservationOfClient(bufferedReader, utilityService, reservationService);
                case "3" -> showListOfUtilities(bufferedReader, utilityService);
                case "4" -> changeUtilityPrice(bufferedReader, utilityService);
            }
        }
    }

    private static void changeUtilityPrice(BufferedReader bufferedReader, UtilityService utilityService) throws IOException {
        int newPrice, utilityId;
        System.out.println("Enter new price of utility:");
        newPrice = enterPositiveInteger(bufferedReader);
        Utility utilityFromMemory;
        do {
            System.out.println("Enter utility id:");
            utilityId = enterPositiveInteger(bufferedReader);
            utilityFromMemory = utilityService.getUtilityById(utilityId);
            if (utilityFromMemory == null) {
                System.out.println("Utility with id = " + utilityId + " does not exist");
                System.out.println("Try again");
            } else {
                utilityService.changePrice(utilityId, BigDecimal.valueOf(newPrice));
                break;
            }
        } while (utilityFromMemory == null);
    }

    static void showListOfUtilities(BufferedReader bufferedReader, UtilityService utilityService) {
        utilityService.showAllUtilities();
    }

    private static void applyUtilityForReservationOfClient(BufferedReader bufferedReader,
                                                           UtilityService utilityService,
                                                           ReservationService reservationService)
            throws IOException {
        int reservationId, utilityId;
        Reservation reservationFromMemory = null;
        do {
            System.out.println("Enter reservation id:");
            reservationId = enterPositiveInteger(bufferedReader);
            reservationFromMemory = reservationService.getReservationByID(reservationId);
            if (reservationFromMemory == null) {
                System.out.println("Reservation with id = " + reservationId + " does not exist");
                System.out.println("Try again");
            } else {
                break;
            }
        } while (reservationFromMemory == null);

        Utility utilityFromMemory;
        do {
            System.out.println("Enter utility id:");
            utilityId = enterPositiveInteger(bufferedReader);
            utilityFromMemory = utilityService.getUtilityById(utilityId);
            if (utilityFromMemory == null) {
                System.out.println("Utility with id = " + utilityId + " does not exist");
                System.out.println("Try again");
            } else {
                break;
            }
        } while (utilityFromMemory == null);
        reservationFromMemory.getUtilities().add(utilityFromMemory);
    }

    private static void addUtility(BufferedReader bufferedReader, UtilityService utilityService) throws IOException {
        System.out.println("Enter utility id:");
        int utilityId = enterPositiveInteger(bufferedReader);
        System.out.println("Enter utility name:");
        String utilityName = bufferedReader.readLine();
        System.out.println("Enter utility price:");
        int price = enterPositiveInteger(bufferedReader);
        utilityService.saveService(utilityId, utilityName, BigDecimal.valueOf(price));
        System.out.println("New utility was successfully added!");
        System.out.println("Name: " + utilityName + "\n" + "Price: $" + price);
    }

    private static int enterPositiveInteger(BufferedReader bufferedReader) {
        int value;
        while (true) {
            try {
                value = Integer.parseInt(bufferedReader.readLine());
                if (value < 0) {
                    System.out.println("Value cannot be negative");
                } else {
                    break;
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Please type a valid number");
            }
        }
        return value;
    }
}
