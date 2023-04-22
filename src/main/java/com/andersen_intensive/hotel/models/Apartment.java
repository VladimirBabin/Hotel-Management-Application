package com.andersen_intensive.hotel.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "apartments")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    @NonNull
    private BigDecimal price;

    @Column(name = "apartment_type")
    @NonNull
    private ApartmentType apartmentType;
    
    @Column(name = "apartment_status")
    @NonNull
    private ApartmentStatus apartmentStatus;

    @OneToOne(mappedBy = "apartment")
    private Reservation reservation;
}