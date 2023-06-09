package com.andersen_intensive.hotel.controller;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/apartment")
public class ApartmentController {
    private final ApartmentService apartmentService;

    @PostMapping("")
    public long addApartment(@RequestBody Apartment apartment) {
        apartmentService.saveApartment(apartment);
        return apartment.getId();
    }

    @GetMapping("/{apartmentId}")
    public Apartment getApartment(@PathVariable long apartmentId) {
        return apartmentService.findById(apartmentId);
    }

    @PutMapping("")
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

    @DeleteMapping("/{apartmentId}")
    public String deleteApartment(@PathVariable long apartmentId){
        apartmentService.deleteById(apartmentId);
        return "Apartment with id " + apartmentId + " was deleted";
    }
}
