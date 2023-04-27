package com.andersen_intensive.hotel.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "apartments")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
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