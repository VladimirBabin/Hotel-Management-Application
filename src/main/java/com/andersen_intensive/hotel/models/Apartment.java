package com.andersen_intensive.hotel.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "apartments")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Apartment {
    @Id
    @Column(name = "apartmentId")
    @NonNull
    private int apartmentId;

    @Column(name = "price")
    @NonNull
    private BigDecimal price;

    @Column(name = "type")
    @NonNull
    private ApartmentType apartmentType;
    
    @Column(name = "status")
    @NonNull
    private ApartmentStatus apartmentStatus;

    @OneToOne(mappedBy = "apartments")
    private List<Reservation> reservations = new ArrayList<>();
}