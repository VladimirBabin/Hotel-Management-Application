package com.andersen_intensive.hotel.controller;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.service.ApartmentService;
import com.andersen_intensive.hotel.service.ClientService;
import com.andersen_intensive.hotel.service.ReservationService;
import com.andersen_intensive.hotel.service.UtilityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {
    private final ApartmentService apartmentService;
    private final ClientService clientService;
    private final UtilityService utilityService;
    private final ReservationService reservationService;

    public HotelController(ApartmentService apartmentService, ClientService clientService, UtilityService utilityService, ReservationService reservationService) {
        this.apartmentService = apartmentService;
        this.clientService = clientService;
        this.utilityService = utilityService;
        this.reservationService = reservationService;
    }

    @PostMapping("/apartment")
    public int addApartment() {
        return 1;
    }

    @GetMapping("/apartment/{apartmentId}")
    public Apartment getApartment(@PathVariable long apartmentId) {
        return null;
    }

    @PutMapping("/apartment")
    public int updateApartment() {
        return 1;
    }

    @GetMapping("/apartment/sort/{sortType}")
    public List<Apartment> getApartmentsSortedBy(@PathVariable String sortType) {
        return null;
    }

    @GetMapping("/apartment/all")
    public List<Apartment> getApartmentsWithoutSorting() {
        return null;
    }

    @PostMapping("/client")
    public int addClient() {
        return 1;
    }

    @GetMapping("/client/{apartmentId}")
    public Apartment getClient(@PathVariable long apartmentId) {
        return null;
    }

    @PutMapping("/client")
    public int updateClient() {
        return 1;
    }

    @GetMapping("/client/sort/{sortType}")
    public List<Apartment> getClientSortedBy(@PathVariable String sortType) {
        return null;
    }

    @GetMapping("/client/all")
    public List<Apartment> getClientWithoutSorting() {
        return null;
    }

}
