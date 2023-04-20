package com.andersen_intensive.hotel.servlets.reservation;

import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.service.ReservationServiceImpl;
import com.andersen_intensive.hotel.servlets.JsonServlet;

import java.time.LocalDate;
import java.util.Map;

public class UpdateReservationServlet extends JsonServlet {

    private final ReservationServiceImpl reservationService;

    public UpdateReservationServlet(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public Response post(String uri, Map<String, String> body) {
        Reservation updatedReservation = new Reservation(Integer.parseInt(body.get("id")),
                Integer.parseInt(body.get("client")),
                Integer.parseInt(body.get("apartment")),
                LocalDate.parse(body.get("checkIn")));
        updatedReservation.setCheckOut(LocalDate.parse(body.get("checkOut")));
        return new Response(reservationService.updateReservation(updatedReservation));    }
}
