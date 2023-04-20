package com.andersen_intensive.hotel.servlets.reservation;

import com.andersen_intensive.hotel.service.ReservationServiceImpl;
import com.andersen_intensive.hotel.service.UtilityServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.util.Map;

public class GetReservationByIdServlet extends JsonServlet {

    private final ReservationServiceImpl reservationService;

    public GetReservationByIdServlet(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        int id = Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
        return new Response(reservationService.getReservationByID(id));
    }
}
