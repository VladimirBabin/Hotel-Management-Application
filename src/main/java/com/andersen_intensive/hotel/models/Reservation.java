package com.andersen_intensive.hotel.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservations")
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in")
    @NonNull
    private LocalDate checkIn;

    @Column(name = "check_out")
    @NonNull
    private LocalDate checkOut;

//    @OneToOne
//    @JoinColumn(name = "client_id")
//    private Client client;

    @OneToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "reservation_utilities",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "utility_id")
    )
    private List<Utility> utilities = new ArrayList<>();
}
