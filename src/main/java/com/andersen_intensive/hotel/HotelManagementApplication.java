package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.management.ConsoleInteraction;
import com.andersen_intensive.hotel.serializer.Serializer;
import com.andersen_intensive.hotel.servlets.ServletsInteraction;

public class HotelManagementApplication {

    public static void main(String[] args) {

//        ConsoleInteraction.showMainMenu();

        ServletsInteraction servletsInteraction = new ServletsInteraction();
        servletsInteraction.run();

//        Serializer serializer = new Serializer();
//        serializer.serialization(ConsoleInteraction.clientService.getClientListWithoutSorting(), ConsoleInteraction.apartmentService.getAll(),
//                ConsoleInteraction.reservationService.getReservationList(), ConsoleInteraction.utilityService.showAllUtilities());
//        serializer.deserialization();
    }
}
