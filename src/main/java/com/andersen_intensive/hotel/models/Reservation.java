package com.andersen_intensive.hotel.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Data
@Setter
@Getter
public class Reservation {
    private static int count = 0;
    private int id;
    private Client client;
    private Apartment apartment;
    private List<Utility> utilities = new ArrayList<>();
    private LocalDate checkIn;
    private LocalDate checkOut;

    //    when creating a reservation first time we need an option without check-out date
    public Reservation(Client client, Apartment apartment, LocalDate checkIn) {
        this.id = ++count;
        this.client = client;
        this.apartment = apartment;
        this.checkIn = checkIn;
    }

    public Reservation(Client client, Apartment apartment, LocalDate checkIn, LocalDate checkOut) {
        this.id = ++count;
        this.client = client;
        this.apartment = apartment;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public double getPrice() {
        double summary = 0;
        long daysBetween = ChronoUnit.DAYS.between(this.checkIn, this.checkOut);
        summary = daysBetween * this.apartment.getApartmentPrice();
        for (Utility utility : utilities) {
            summary += utility.getPrice();
        }
        return summary;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + id + "\n" +
                "Client: " + client.getLastName() + " " + client.getFirstName() + "\n" +
                "Apartment: " + apartment + "\n" +
                "Services: " + utilities + "\n" +
                "Check-in: " + checkIn + "\n" +
                "Check-out: " + (checkOut == null ? "haven't checked out yet" : checkOut);
    }
}
