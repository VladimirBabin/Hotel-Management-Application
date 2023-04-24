package com.andersen_intensive.hotel.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    @NonNull
    private String firstName;

    @Column(name = "lastName")
    @NonNull
    private String lastName;

    @Column(name = "phoneNumber")
    @NonNull
    private String phoneNumber;

    @OneToOne(mappedBy = "client")
    private Reservation reservation;
}