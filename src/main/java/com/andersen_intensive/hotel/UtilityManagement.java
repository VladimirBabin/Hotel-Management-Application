package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Utility;
import com.andersen_intensive.hotel.service.UtilityService;

import java.io.BufferedReader;
import java.io.IOException;

// Georgi
public class UtilityManagement {

    private static final String UTILITY_MANAGEMENT_MENU = "1. Add service" + "\n" +
            "2. Apply a service for client" + "\n" +
            "3. Show list of services" + "\n" +
            "4. Change service price" + "\n" + "\n" +
            "To go back type 0";

    public static void showUtilityManagementMenu(BufferedReader bufferedReader, UtilityService utilityService)
            throws IOException {
        System.out.println(UTILITY_MANAGEMENT_MENU);
        while (true) {
            String input = bufferedReader.readLine();
            switch (input) {
                case "0":
                    return;
                case "1":
                    addUtility(bufferedReader, utilityService);
                    break;
                case "2":
                    applyUtilityForClient(bufferedReader, utilityService);
                    break;
                case "3":
                    showListOfUtilities(bufferedReader, utilityService);
                    break;
                case "4":
                    changeUtilityPrice(bufferedReader, utilityService);
                    break;
            }
        }
    }

    private static void changeUtilityPrice(BufferedReader bufferedReader, UtilityService utilityService) {
        int newPrice, utilityId;
        System.out.println("Enter new price of utility:");
        newPrice = enterPositiveInteger(bufferedReader);
        Utility utilityFromMemory;
        do {
            System.out.println("Enter utility id:");
            utilityId = enterPositiveInteger(bufferedReader);
            utilityFromMemory = utilityService.getUtilityById(utilityId);
            if (utilityFromMemory == null) {
                System.out.println("Utility with id = " + utilityId + "does not exist");
                System.out.println("Try again");
            } else {
                utilityService.changePrice(utilityId, newPrice);
                break;
            }
        } while (utilityFromMemory != null);
    }

    //  leave it package private, so we can call it from ConsoleInteraction
    static void showListOfUtilities(BufferedReader bufferedReader, UtilityService utilityService) {
        utilityService.showAllUtilities();
    }

    private static void applyUtilityForClient(BufferedReader bufferedReader, UtilityService utilityService) {
    }

    private static void addUtility(BufferedReader bufferedReader, UtilityService utilityService) throws IOException {
        System.out.println("Enter utility name:");
        String utilityName = bufferedReader.readLine();
        System.out.println("Enter utility price:");
        int price = enterPositiveInteger(bufferedReader);
        utilityService.saveService(utilityName, price);
    }

    private static int enterPositiveInteger(BufferedReader bufferedReader) {
        int newPrice;
        while (true) {
            try {
                newPrice = Integer.parseInt(bufferedReader.readLine());
                if (newPrice < 0) {
                    System.out.println("Value cannot be negative");
                } else {
                    break;
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Please type a valid number");
            }
        }
        return newPrice;
    }
}
