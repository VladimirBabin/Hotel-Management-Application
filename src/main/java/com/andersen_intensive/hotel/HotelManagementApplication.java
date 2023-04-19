package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.management.ConsoleInteraction;
import com.andersen_intensive.hotel.serializer.Serializer;

public class HotelManagementApplication {

    public static void main(String[] args) {

        ConsoleInteraction.showMainMenu();

        Serializer serializer = new Serializer();
        serializer.serialization(ConsoleInteraction.clientService.getClientListWithoutSorting(), ConsoleInteraction.apartmentService.getAll(),
                ConsoleInteraction.reservationService.getReservationList(), ConsoleInteraction.utilityService.showAllUtilities());
        serializer.deserialization();
    }
}
