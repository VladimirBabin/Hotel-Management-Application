package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApartmentServiceTest {
    @Mock
    private ApartmentRepository apartmentRepository;
    private ApartmentService apartmentService;
    private final Apartment apartment = new Apartment(new BigDecimal("1000.00"), ApartmentType.SINGLE, ApartmentStatus.AVAILABLE);
    private final Long apartmentId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        apartmentService = new ApartmentService(apartmentRepository);
    }

    @Test
    void saveApartmentWhenPriceIsNegative() {
        Apartment apartmentWithNegativePrice = new Apartment(new BigDecimal("-1000.00"), ApartmentType.DOUBLE, ApartmentStatus.AVAILABLE);
        assertThrows(IllegalArgumentException.class, () -> apartmentService.saveApartment(apartmentWithNegativePrice));
        verifyNoInteractions(apartmentRepository);
    }

    @Test
    void saveApartmentWhenPriceIsPositive() {
        doNothing().when(apartmentRepository).save(apartment);
        Apartment result = apartmentService.saveApartment(apartment);
        assertSame(apartment, result);
        verify(apartmentRepository, times(1)).save(apartment);
        verifyNoMoreInteractions(apartmentRepository);
    }

    @Test
    void findById() {
        when(apartmentRepository.findById(apartmentId)).thenReturn(Optional.of(apartment));
        Apartment result = apartmentService.findById(apartmentId);
        assertSame(apartment, result);
        verify(apartmentRepository, times(1)).findById(apartmentId);
        verifyNoMoreInteractions(apartmentRepository);
    }

    @Test
    void findByIdNotExist() {
        when(apartmentRepository.findById(apartmentId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> apartmentService.findById(apartmentId));
        verify(apartmentRepository, times(1)).findById(apartmentId);
        verifyNoMoreInteractions(apartmentRepository);
    }

    @Test
    public void testUpdateStatus() {
        ApartmentService apartmentService = new ApartmentService(apartmentRepository);
        when(apartmentRepository.findById(1L)).thenReturn(Optional.of(new Apartment(new BigDecimal(150), ApartmentType.SINGLE, ApartmentStatus.AVAILABLE)));
        Apartment updatedApartment = apartmentService.updateStatus(1L, ApartmentStatus.OCCUPIED);
        verify(apartmentRepository, times(1)).findById(1L);
        verify(apartmentRepository, times(1)).update(updatedApartment);
        assertEquals(ApartmentStatus.OCCUPIED, updatedApartment.getApartmentStatus());
    }
}
