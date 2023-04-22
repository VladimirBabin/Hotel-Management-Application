package com.andersen_intensive.hotel;

import com.andersen_intensive.hotel.servlets.ServletsInteraction;

public class HotelManagementApplication {

    public static void main(String[] args) {
        ServletsInteraction servletsInteraction = new ServletsInteraction();
        servletsInteraction.run();
    }
}
