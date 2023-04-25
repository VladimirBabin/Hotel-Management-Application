package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ApartmentServiceTest {
    private final ApartmentRepository apartmentRepository = mock(ApartmentRepository.class);
    private final ApartmentService apartmentService = new ApartmentService(apartmentRepository);

    @Test
    public void testSaveApartmentSuccess() {
        Apartment apartment = new Apartment(new BigDecimal("100"), ApartmentType.SINGLE, ApartmentStatus.AVAILABLE);
        when(apartmentRepository.findById(apartment.getId())).thenReturn(Optional.of(apartment));
        Apartment result = apartmentService.findById(apartment.getId());
        assertEquals(apartment, result);
    }

    @Test
    void saveApartmentWhenPriceIsNegative() {
        Apartment apartmentWithNegativePrice = new Apartment(new BigDecimal("-1000.00"), ApartmentType.DOUBLE, ApartmentStatus.AVAILABLE);
        assertThrows(IllegalArgumentException.class, () -> apartmentService.saveApartment(apartmentWithNegativePrice));
    }

    @Test
    public void testShowAll() {
        List<Apartment> apartments = new ArrayList<>();
        apartments.add(new Apartment(new BigDecimal("100"), ApartmentType.SINGLE, ApartmentStatus.AVAILABLE));
        apartments.add(new Apartment(new BigDecimal("150"), ApartmentType.DOUBLE, ApartmentStatus.OCCUPIED));
        when(apartmentRepository.findAll()).thenReturn(apartments);
        List<Apartment> result = apartmentService.showAll();
        assertEquals(apartments, result);
    }

    @Test
    public void testFindApartmentByIdSuccess() {
        Apartment apartment = new Apartment(new BigDecimal("100"), ApartmentType.SINGLE, ApartmentStatus.AVAILABLE);
        when(apartmentRepository.findById(apartment.getId())).thenReturn(Optional.of(apartment));
        Apartment result = apartmentService.findById(apartment.getId());
        assertEquals(apartment, result);
    }

    @Test
    public void testFindApartmentByIdNotFound() {
        Long id = 1L;
        when(apartmentRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> apartmentService.findById(id));
    }

    @Test
    public void testSortByStatus() {
        List<Apartment> apartments = Arrays.asList(
                new Apartment(new BigDecimal("110"), ApartmentType.SINGLE, ApartmentStatus.AVAILABLE),
                new Apartment(new BigDecimal("200"), ApartmentType.SINGLE, ApartmentStatus.OCCUPIED),
                new Apartment(new BigDecimal("150"), ApartmentType.SINGLE, ApartmentStatus.UNAVAILABLE));
        when(apartmentRepository.findAll()).thenReturn(apartments);
        List<Apartment> sortedApartments = apartmentService.sortByStatus();
        assertEquals(ApartmentStatus.AVAILABLE, sortedApartments.get(0).getApartmentStatus());
        assertEquals(ApartmentStatus.OCCUPIED, sortedApartments.get(1).getApartmentStatus());
        assertEquals(ApartmentStatus.UNAVAILABLE, sortedApartments.get(2).getApartmentStatus());
    }

    @Test
    public void testUpdateStatus() {
        ApartmentService apartmentService = new ApartmentService(apartmentRepository);
        when(apartmentRepository.findById(1L)).thenReturn(Optional.of(new Apartment(new BigDecimal(150), ApartmentType.SINGLE, ApartmentStatus.AVAILABLE)));
        Apartment updatedApartment = apartmentService.updateStatus(1L, ApartmentStatus.OCCUPIED);
        verify(apartmentRepository, times(1)).findById(1L);
        verify(apartmentRepository, times(1)).save(updatedApartment);
        assertEquals(ApartmentStatus.OCCUPIED, updatedApartment.getApartmentStatus());
    }
}