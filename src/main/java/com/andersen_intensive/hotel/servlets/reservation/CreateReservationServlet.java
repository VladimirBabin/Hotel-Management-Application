//package com.andersen_intensive.hotel.servlets.reservation;
//
//import com.andersen_intensive.hotel.models.Reservation;
//import com.andersen_intensive.hotel.service.ReservationService;
//import com.andersen_intensive.hotel.servlets.JsonServlet;
//import lombok.RequiredArgsConstructor;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//import java.util.Map;
//
//@RequiredArgsConstructor
//public class CreateReservationServlet extends JsonServlet {
//
//    private final ReservationService reservationService;
//
//    @Override
//    public Response post(String uri, Map<String, String> body) {
//        LocalDate checkIn = null;
//        LocalDate checkOut = null;
//        try {
//            checkIn = LocalDate.parse(body.get("check_in"));
//            checkOut = LocalDate.parse(body.get("check_out"));
//        } catch (DateTimeParseException e) {
//            throw new IllegalArgumentException("Date should be in format yyyy-MM-dd");
//        }
//
//        Reservation reservation = new Reservation(checkIn, checkOut);
//        Long apartmentId = Long.parseLong(body.get("apartment_id"));
//        Long clientId = Long.parseLong(body.get("client_id"));
//
//        return new Response(reservationService.createReservation(reservation, apartmentId, clientId));
//    }
//}
