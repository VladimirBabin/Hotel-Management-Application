package com.andersen_intensive.hotel.servlets.reservation;

import com.andersen_intensive.hotel.DEPRECATEDservice.ReservationServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.util.Map;

public class GetReservationsServlet extends JsonServlet {

    private final ReservationServiceImpl reservationService;

    public GetReservationsServlet(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public Response get(String uri, Map<String, String[]> parameters) {
        return new Response(reservationService.getReservationList());
    }
}
