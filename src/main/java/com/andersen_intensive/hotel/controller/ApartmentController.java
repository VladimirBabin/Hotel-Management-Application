package com.andersen_intensive.hotel.controller;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.service.ApartmentService;
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
    public long addApartment(@RequestBody Apartment apartment) {
        Apartment newApartment = apartmentService.saveApartment(apartment);
        return newApartment.getId();
    }

    @GetMapping("/{apartmentId}")
    public Apartment getApartment(@PathVariable long apartmentId) {
        return apartmentService.findById(apartmentId);
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
