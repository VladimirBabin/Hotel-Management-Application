package com.andersen_intensive.hotel;

public class HotelManagementApplication {

    public static void main(String[] args) {

        ConsoleInteraction.showMainMenu();

        Serializer serializer = new Serializer();
        serializer.serialization(ConsoleInteraction.clientService.getClientListWithoutSorting(), ConsoleInteraction.apartmentService.getAll(),
                ConsoleInteraction.reservationService.getReservationList(), ConsoleInteraction.utilityService.showAllUtilities());
    }
}
