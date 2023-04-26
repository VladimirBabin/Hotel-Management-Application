package com.andersen_intensive.hotel.controller;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.service.ClientService;
import com.andersen_intensive.hotel.service.ReservationService;
import com.andersen_intensive.hotel.service.UtilityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    private final ApartmentService apartmentService;
    private final ClientService clientService;
    private final UtilityService utilityService;
    private final ReservationService reservationService;

    public ReservationController(ApartmentService apartmentService, ClientService clientService, UtilityService utilityService, ReservationService reservationService) {
        this.apartmentService = apartmentService;
        this.clientService = clientService;
        this.utilityService = utilityService;
        this.reservationService = reservationService;
    }

    @PostMapping("")
    public long createReservation(@RequestBody Reservation reservation) {
        Reservation newReservation = reservationService.createReservation(reservation);
        return newReservation.getId();
    }

    @GetMapping("/{reservationId}")
    public Reservation getReservationById(@PathVariable long reservationId) {
        return reservationService.getReservationByID(reservationId);
    }

}
