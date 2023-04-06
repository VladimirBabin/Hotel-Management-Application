package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.repository.ApartmentRepositoryImpl;
import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.service.ApartmentServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Sveta
public class ApartmentManagement {

    private static final ApartmentServiceImpl apartmentService = ApartmentServiceImpl.getInstance();

    private static final String APARTMENT_MANAGEMENT_MENU = "1. Add apartment" + "\n" +
            "2. Show list of apartments" + "\n" +
            "3. Change apartment status" + "\n" +
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
                        changeApartmentStatus(bufferedReader);
                        break;
                    case "4":
                        changeApartmentPrice(bufferedReader);
                        break;
                }
            }
        }
    }

    private static void addApartment(BufferedReader bufferedReader) throws IOException {

        System.out.println("Enter apartment number:");
        int apartmentNumber = Integer.parseInt(bufferedReader.readLine());

        System.out.println("Enter price per night:");
        double apartmentPrice = enterApartmentPrice(bufferedReader);

        System.out.println("Enter room type: 1 for single bed, 2 for double bed:");
        ApartmentType apartmentType = enterApartmentType(bufferedReader);

        Apartment apartment = new Apartment(apartmentNumber, apartmentPrice, apartmentType);
        System.out.println(apartment);
        ApartmentRepositoryImpl apartmentMap = ApartmentRepositoryImpl.getInstance();
        apartmentMap.addApartment(apartment);
        apartmentMap.getAllApartments();

        List<Apartment> apartments = apartmentMap.getAllApartments();
        System.out.println("Apartment added successfully!");
        System.out.println(" ");
    }
    private static void showListOfAvailableApartments(BufferedReader bufferedReader) throws IOException {
        System.out.println("List of available apartments:" + "\n");
        ApartmentRepositoryImpl apartmentMap = ApartmentRepositoryImpl.getInstance();
        List<Apartment> apartments = apartmentMap.getAllApartments();

        if (apartments.isEmpty()) {
            System.out.println("No apartments found.");
        } else {
            System.out.println("Sort by: 1 - price, 2 - status, 3 - type, 4 - no sorting");
            int sortOption = Integer.parseInt(bufferedReader.readLine());

            switch (sortOption) {
                case 1:
                    apartments.sort(Comparator.comparingDouble(Apartment::getApartmentPrice));
                    break;
                case 2:
                    apartments.sort((a1, a2) -> a1.getApartmentStatus().compareTo(a2.getApartmentStatus()));
                    break;
                case 3:
                    apartments.sort((a1, a2) -> a1.getApartmentType().compareTo(a2.getApartmentType()));
                    break;
                default:
                    break;
            }

            for (Apartment ap : apartments) {
                System.out.println(ap);
            }
        }
        System.out.println(" ");
    }
      private static void changeApartmentStatus(BufferedReader bufferedReader) throws IOException {
       System.out.println("Enter apartment number:");
       int apartmentNumber = Integer.parseInt(bufferedReader.readLine());

       ApartmentService apartmentService = ApartmentServiceImpl.getInstance();

       Apartment apartment = apartmentService.getApartmentByNumber(apartmentNumber);

       if (apartment == null) {
           System.out.println("Apartment not found!");
           return;
       }

       System.out.println("Enter new status (1 for available, 2 for occupied, 3 for unavailable):");
       String statusStr = bufferedReader.readLine();
       ApartmentStatus newStatus = ApartmentStatus.valueOfLabel(statusStr);

       apartmentService.setApartmentStatus(apartmentNumber, newStatus);

       System.out.println("Apartment status has been updated to " + newStatus.name().toLowerCase() + ".");
       System.out.println(" ");
   }

    /*private static void changeApartmentPrice(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter apartment number:");
        int apartmentNumber = Integer.parseInt(bufferedReader.readLine());

        //       Эти операции нужно выполнять через вызов сервиса
        ApartmentRepositoryImpl apartmentRepository = ApartmentRepositoryImpl.getInstance();
        Apartment apartment = apartmentRepository.getApartmentByNumber(apartmentNumber);

        if (apartment == null) {
            System.out.println("Apartment not found!");
            return;
        }

        System.out.println("Enter new price per night:");
        double newPrice = enterApartmentPrice(bufferedReader);

        apartment.setPrice(newPrice);
        //       Эти операции нужно выполнять через вызов сервиса
        apartmentRepository.updateApartment(apartment);

        System.out.println("Apartment price has been updated.");
        System.out.println(" ");
    }*/
    private static void changeApartmentPrice(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter apartment number:");
        int apartmentNumber = Integer.parseInt(bufferedReader.readLine());

        // Получаем объект апартаментов через сервис
        ApartmentService apartmentService = ApartmentServiceImpl.getInstance();
        Apartment apartment = apartmentService.getApartmentByNumber(apartmentNumber);

        if (apartment == null) {
            System.out.println("Apartment not found!");
            return;
        }

        System.out.println("Enter new price per night:");
        double newPrice = enterApartmentPrice(bufferedReader);

        apartment.setPrice(newPrice);

        // Вызываем метод сервиса для обновления данных об апартаментах
        apartmentService.updateApartmentPrice(apartmentNumber, newPrice);

        System.out.println("Apartment price has been updated.");
        System.out.println(" ");
    }


    private static ApartmentType enterApartmentType(BufferedReader bufferedReader) {
        try {
            return ApartmentType.valueOfLabel(bufferedReader.readLine());
        } catch (IllegalArgumentException | IOException exp) {
            System.out.println("Invalid apartment type! Please, choose 1 for single bed or 2 for double bed:");
            return enterApartmentType(bufferedReader);
        }
    }

    private static double enterApartmentPrice(BufferedReader bufferedReader) {
        try {
            return Double.parseDouble(bufferedReader.readLine());
        } catch (NumberFormatException | IOException exp) {
            System.out.println("Invalid apartment price! Please, enter a valid double number. " +
                    "Decimals should be separated by point (.)");
            return enterApartmentPrice(bufferedReader);
        }
    }
}


