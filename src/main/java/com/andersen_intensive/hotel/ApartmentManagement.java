package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.service.ApartmentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class ApartmentManagement {
    private static final String APARTMENT_MANAGEMENT_MENU = """
            1. Add apartment
            2. Show list of apartments
            3. Change apartment status
            4. Change apartment price

            To go back type 0""";

    static void showApartmentManagementMenu(BufferedReader bufferedReader, ApartmentService apartmentService) throws IOException {

        while (true) {
            System.out.println(APARTMENT_MANAGEMENT_MENU);
            String input = bufferedReader.readLine();
            switch (input) {
                case "0":
                    return;
                case "1":
                    addApartment(bufferedReader, apartmentService);
                    break;
                case "2":
                    showListOfApartments(bufferedReader, apartmentService);
                    break;
                case "3":
                    changeApartmentStatus(bufferedReader, apartmentService);
                    break;
                case "4":
                    changeApartmentPrice(bufferedReader, apartmentService);
                    break;
            }
        }
    }

    private static void addApartment(BufferedReader bufferedReader, ApartmentService apartmentService) throws IOException {
        System.out.println("Enter apartment number:");
        int apartmentId;

        while (true) {
            try {
                apartmentId = Integer.parseInt(bufferedReader.readLine());
                if (apartmentId <= 0) {
                    System.out.println("Apartment number must be greater than 0. Please try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid apartment number.");
            }
        }
        if (apartmentService.isValid(apartmentId)) {
            System.out.println("Apartment with such number already exists!");
            System.out.println(" ");
            return;
        }

        System.out.println("Enter price per night:");
        BigDecimal apartmentPrice = enterApartmentPrice(bufferedReader);
        System.out.println("Enter room type: 1 for single bed, 2 for double bed:");
        ApartmentType apartmentType = enterApartmentType(bufferedReader);
        Apartment apartment = new Apartment(apartmentId, apartmentPrice, apartmentType);
        System.out.println(apartment);
        apartmentService.add(apartment);
        System.out.println("Apartment added successfully!");
        System.out.println(" ");
    }

    private static void showListOfApartments(BufferedReader bufferedReader, ApartmentService apartmentService) throws IOException {
        System.out.println("List of available apartments:" + "\n");
        List<Apartment> apartments = apartmentService.getAll();
        if (apartments.isEmpty()) {
            System.out.println("No apartments found.");
        } else {
            System.out.println("Sort by: 1 - price, 2 - status, 3 - type, 4 - no sorting");
            int sortOption = Integer.parseInt(bufferedReader.readLine());
            switch (sortOption) {
                case 1 -> apartments.sort(Comparator.comparing(Apartment::getApartmentPrice));
                case 2 -> apartments.sort(Comparator.comparing(Apartment::getApartmentStatus));
                case 3 -> apartments.sort(Comparator.comparing(Apartment::getApartmentType));
                default -> {
                }
            }
            for (Apartment ap : apartments) {
                System.out.println(ap.toStringList());
            }
        }
        System.out.println(" ");
    }

    private static void changeApartmentStatus(BufferedReader bufferedReader, ApartmentService apartmentService) throws IOException {
        System.out.println("Enter apartment number:");
        int apartmentId = Integer.parseInt(bufferedReader.readLine());
        Apartment apartment = apartmentService.getById(apartmentId);

        if (apartment == null) {
            System.out.println("Apartment not found!");
            return;
        }
        System.out.println("Enter new status (1 for available, 2 for occupied, 3 for unavailable):");
        String statusStr = bufferedReader.readLine();
        ApartmentStatus newStatus = ApartmentStatus.values()[Integer.parseInt(statusStr) - 1];
        apartmentService.setStatus(apartmentId, newStatus);
        System.out.println("Apartment status has been updated to " + newStatus.name().toLowerCase() + ".");
        System.out.println(" ");
    }

    private static void changeApartmentPrice(BufferedReader bufferedReader, ApartmentService apartmentService) throws IOException {
        System.out.println("Enter apartment number:");
        int apartmentId = Integer.parseInt(bufferedReader.readLine());
        Apartment apartment = apartmentService.getById(apartmentId);
        if (apartment == null) {
            System.out.println("Apartment not found!");
            return;
        }

        System.out.println("Enter new price per night:");
        BigDecimal newPrice = enterApartmentPrice(bufferedReader);
        apartment.setPrice(newPrice);
        apartmentService.updatePrice(apartmentId, newPrice);
        System.out.println("Apartment price has been updated.");
        System.out.println(" ");
    }


    private static ApartmentType enterApartmentType(BufferedReader bufferedReader) {
        try {
            return ApartmentType.values()[Integer.parseInt(bufferedReader.readLine()) - 1];
        } catch (IllegalArgumentException | IOException exp) {
            System.out.println("Invalid apartment type! Please, choose 1 for single bed or 2 for double bed:");
            return enterApartmentType(bufferedReader);
        }
    }

    private static BigDecimal enterApartmentPrice(BufferedReader bufferedReader) {
        try {
            return new BigDecimal(bufferedReader.readLine());
        } catch (NumberFormatException | IOException exp) {
            System.out.println("Invalid apartment price! Please, enter a valid double number. " +
                    "Decimals should be separated by point (.)");
            return enterApartmentPrice(bufferedReader);
        }
    }
}


