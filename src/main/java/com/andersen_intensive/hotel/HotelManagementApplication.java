package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.Client;
import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.models.Utility;

public class HotelManagementApplication {

    public static void main(String[] args) {

        ConsoleInteraction.showMainMenu();

        Serializer serializer = new Serializer();
        serializer.serialization(ConsoleInteraction.clientService.getClientListWithoutSorting(), ConsoleInteraction.apartmentService.getAll(),
                ConsoleInteraction.reservationService.getReservationList(), ConsoleInteraction.utilityService.showAllUtilities());
    }
}
