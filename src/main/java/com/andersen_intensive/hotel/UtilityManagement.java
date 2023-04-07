package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.service.UtilityService;

import java.io.BufferedReader;
import java.io.IOException;

// Georgi
public class UtilityManagement {

    private static final String UTILITY_MANAGEMENT_MENU = "1. Add service" + "\n" +
            "2. Apply a service for client" + "\n" +
            "3. Show list of services" + "\n" +
            "4. Change service price" + "\n" + "\n" +
            "To go back type 0";;

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
    }
//  leave it package private, so we can call it from ConsoleInteraction
    static void showListOfUtilities(BufferedReader bufferedReader, UtilityService utilityService) {
    }

    private static void applyUtilityForClient(BufferedReader bufferedReader, UtilityService utilityService) {
    }

    private static void addUtility(BufferedReader bufferedReader, UtilityService utilityService) {
    }
}
