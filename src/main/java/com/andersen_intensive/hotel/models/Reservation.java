package com.andersen_intensive.hotel.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private static int count = 0;
    private int id;
    private Client client;
    private Apartment apartment;
    private List<Service> services = new ArrayList<>();
    private LocalDate checkIn;
    private LocalDate checkOut;

//    when creating a reservation first time we need an option without check-out date
    public Reservation(Client client, Apartment apartment, LocalDate checkIn) {
        this.id = ++count;
        this.client = client;
        this.apartment = apartment;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
    public Reservation(Client client, Apartment apartment, LocalDate checkIn, LocalDate checkOut) {
        this.id = ++count;
        this.client = client;
        this.apartment = apartment;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public List<Service> getServices() {
        return services;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public double getPrice() {
        double summary = 0;
        long daysBetween = ChronoUnit.DAYS.between(this.checkIn, this.checkOut);
        summary = daysBetween * this.apartment.getApartmentPrice();
        for (Service service : services) {
            summary += service.getPrice();
        }
        return summary;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client=" + client +
                ", apartment=" + apartment +
                ", services=" + services +
                ", checkIn=" + checkIn +
                ", checkOut=" + (checkOut == null ? "haven't checked out yet" : checkOut) +
                '}';
    }
}
