package com.andersen_intensive.hotel.controller;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.service.ApartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartment")
public class ApartmentController {
    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @PostMapping("/")
    public int addApartment() {
        return 1;
    }

    @GetMapping("/{apartmentId}")
    public Apartment getApartment(@PathVariable long apartmentId) {
        return null;
    }

    @PutMapping("/")
    public int updateApartment() {
        return 1;
    }

    @GetMapping("/sort/{sortType}")
    public List<Apartment> getApartmentsSortedBy(@PathVariable String sortType) {
        return null;
    }

    @GetMapping("/all")
    public List<Apartment> getApartmentsWithoutSorting() {
        return null;
    }
}
