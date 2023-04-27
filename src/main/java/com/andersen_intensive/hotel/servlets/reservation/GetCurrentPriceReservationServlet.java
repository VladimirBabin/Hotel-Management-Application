//package com.andersen_intensive.hotel.servlets.reservation;
//
//import com.andersen_intensive.hotel.service.ReservationService;
//import com.andersen_intensive.hotel.servlets.JsonServlet;
//import lombok.RequiredArgsConstructor;
//
//import java.util.Map;
//
//@RequiredArgsConstructor
//public class GetCurrentPriceReservationServlet extends JsonServlet {
//
//    private final ReservationService reservationService;
//
//    @Override
//    public Response get(String uri, Map<String, String[]> parameters) {
//        Long id = Long.parseLong(uri.substring(uri.lastIndexOf('/') + 1));
//        return new Response(reservationService.getCurrentPrice(id));
//    }
//}
