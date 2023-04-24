package com.andersen_intensive.hotel.servlets.reservation;

import com.andersen_intensive.hotel.service.ReservationService;
import com.andersen_intensive.hotel.servlets.JsonServlet;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class CreateUtilityForReservation extends JsonServlet {

    private final ReservationService reservationService;

    @Override
    public Response post(String uri, Map<String, String> body) {
        return new Response(reservationService.createUtilityForReservation(
                Long.parseLong(body.get("reservation_id")),
                Long.parseLong(body.get("utility_id"))
        ));
    }
}
