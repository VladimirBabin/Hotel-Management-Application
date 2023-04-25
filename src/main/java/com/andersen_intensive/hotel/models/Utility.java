package com.andersen_intensive.hotel.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utilities")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Utility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "price")
    @NonNull
    private BigDecimal price;

    @ManyToMany(mappedBy = "utilities")
    private List<Reservation> reservations = new ArrayList<>();
}
