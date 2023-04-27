package com.andersen_intensive.hotel.controller;

import com.andersen_intensive.hotel.dto.ReservationDto;
import com.andersen_intensive.hotel.models.Reservation;
import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.service.ClientService;
import com.andersen_intensive.hotel.service.ReservationService;
import com.andersen_intensive.hotel.service.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public long createReservation(@RequestBody ReservationDto reservationDto) {
        Reservation reservation = reservationService.createReservation(reservationDto);
        return reservation.getId();
    }

    @GetMapping("/{reservationId}")
    public Reservation getReservationById(@PathVariable long reservationId) {
        return reservationService.getReservationByID(reservationId);
    }

    @GetMapping("/all")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping("/utility")
    public Reservation addUtilitiesToReservation(@RequestBody ReservationDto reservationDto) {
        return reservationService.addUtilityForReservation(reservationDto);
    }

    @GetMapping("/check-out/{reservationId}")
    public BigDecimal checkOutAndGetCurrentPrice(@PathVariable long reservationId) {
        return reservationService.getCurrentPrice(reservationId);
    }
}
