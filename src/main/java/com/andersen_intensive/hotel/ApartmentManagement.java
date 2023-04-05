package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentType;

import java.io.BufferedReader;
import java.io.IOException;

// Sveta
public class ApartmentManagement {

    private static final String APARTMENT_MANAGEMENT_MENU = "1. Add apartment" + "\n" +
            "2. Show list of apartments" + "\n" +
            "3. Change apartment status to be unavailable" + "\n" +
            "4. Change apartment price" + "\n" + "\n" +
            "To go back type 0";

    static void showApartmentManagementMenu(BufferedReader bufferedReader) throws IOException {
        while (true) {
            while (true) {
                System.out.println(APARTMENT_MANAGEMENT_MENU);
                String input = bufferedReader.readLine();
                switch (input) {
                    case "0":
                        return;
                    case "1":
                        addApartment(bufferedReader);
                        break;
                    case "2":
                        showListOfAvailableApartments(bufferedReader);
                        break;
                    case "3":
                        changeApartmentStatusToBeUnavailable(bufferedReader);
                        break;
                    case "4":
                        changeApartmentPrice(bufferedReader);
                        break;
                }
            }
        }
    }

    private static void addApartment(BufferedReader bufferedReader) throws IOException {     //Sv

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

    static void showListOfAvailableApartments(BufferedReader bufferedReader) throws IOException {
        System.out.println("List of available apartments" + "\n" + "\n" +
                "To go back type 1");
        while (true) {
            String input = bufferedReader.readLine();
            if (input.equals("1")) {
                return;
            }
        }
    }

    private static void changeApartmentStatusToBeUnavailable(BufferedReader bufferedReader) throws IOException {

    }

    private static void changeApartmentPrice(BufferedReader bufferedReader) throws IOException {

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
}


