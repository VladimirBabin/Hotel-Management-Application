package com.andersen_intensive.hotel.controller;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.models.Utility;
import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.service.ClientService;
import com.andersen_intensive.hotel.service.ReservationService;
import com.andersen_intensive.hotel.service.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    private final ApartmentService apartmentService;
    private final ClientService clientService;
    private final UtilityService utilityService;
    private final ReservationService reservationService;

    @PostMapping("")
    public long createReservation(@RequestBody Reservation reservation) {
        reservationService.createReservation(reservation);
        return reservation.getId();
    }

    @GetMapping("/{reservationId}")
    public Reservation getReservationById(@PathVariable long reservationId) {
        return reservationService.getReservationByID(reservationId);
    }

    @PutMapping("")
    public Reservation addUtilitiesToReservation(@RequestBody Reservation reservation) {
        return reservationService.addUtilitiesToReservation(reservation);
    }
}
