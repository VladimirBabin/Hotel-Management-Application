package com.andersen_intensive.hotel.service;

import com.andersen_intensive.hotel.models.Apartment;
import com.andersen_intensive.hotel.models.ApartmentStatus;
import com.andersen_intensive.hotel.models.ApartmentType;
import com.andersen_intensive.hotel.repository.ApartmentRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
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
    void saveApartment_whenPriceIsNegative_shouldThrowIllegalArgumentException() {
        Apartment apartmentWithNegativePrice = new Apartment(new BigDecimal("-1000.00"), ApartmentType.DOUBLE, ApartmentStatus.AVAILABLE);

        assertThrows(IllegalArgumentException.class, () -> apartmentService.saveApartment(apartmentWithNegativePrice));
        verifyNoInteractions(apartmentRepository);
    }

    @Test
    void saveApartment_whenPriceIsPositive_shouldCallApartmentRepositorySaveMethod() {
        doNothing().when(apartmentRepository).save(apartment);

        Apartment result = apartmentService.saveApartment(apartment);

        assertSame(apartment, result);
        verify(apartmentRepository, times(1)).save(apartment);
        verifyNoMoreInteractions(apartmentRepository);
    }

    @Test
    void showAll_whenCalled_shouldCallApartmentRepositoryFindAllMethod() {
        when(apartmentRepository.findAll()).thenReturn(Collections.singletonList(apartment));

        List<Apartment> result = apartmentService.showAll();

        assertSame(Collections.singletonList(apartment), result);
        verify(apartmentRepository, times(1)).findAll();
        verifyNoMoreInteractions(apartmentRepository);
    }

    @Test
    void findById_whenApartmentExists_shouldReturnApartment() {
        when(apartmentRepository.findById(apartmentId)).thenReturn(Optional.of(apartment));

        Apartment result = apartmentService.findById(apartmentId);

        assertSame(apartment, result);
        verify(apartmentRepository, times(1)).findById(apartmentId);
        verifyNoMoreInteractions(apartmentRepository);
    }

    @Test
    void findById_whenApartmentDoesNotExist_shouldThrowEntityNotFoundException() {
        when(apartmentRepository.findById(apartmentId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> apartmentService.findById(apartmentId));
        verify(apartmentRepository, times(1)).findById(apartmentId);
        verifyNoMoreInteractions(apartmentRepository);
    }
}
