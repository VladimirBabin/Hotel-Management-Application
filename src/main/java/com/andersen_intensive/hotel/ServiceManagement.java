package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.service.ServiceServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;

// Georgi
public class ServiceManagement {

    private static final ServiceServiceImpl serviceService = ServiceServiceImpl.getInstance();

    private static final String SERVICE_MANAGEMENT_MENU = "1. Add service" + "\n" +
            "2. Apply a service for client" + "\n" +
            "3. Show list of services" + "\n" +
            "4. Change service price" + "\n" + "\n" +
            "To go back type 0";;

    public static void showServiceManagementMenu(BufferedReader bufferedReader) throws IOException {
        System.out.println(SERVICE_MANAGEMENT_MENU);
        while (true) {
            String input = bufferedReader.readLine();
            switch (input) {
                case "0":
                    return;
                case "1":
                    addService(bufferedReader);
                    break;
                case "2":
                    applyServiceForClient(bufferedReader);
                    break;
                case "3":
                    showListOfServices(bufferedReader);
                    break;
                case "4":
                    changeServicePrice(bufferedReader);
                    break;
            }
        }
    }

    private static void changeServicePrice(BufferedReader bufferedReader) {
    }
//  leave it package private, so we can call it from ConsoleInteraction
    static void showListOfServices(BufferedReader bufferedReader) {
    }

    private static void applyServiceForClient(BufferedReader bufferedReader) {
    }

    private static void addService(BufferedReader bufferedReader) {
    }
}
