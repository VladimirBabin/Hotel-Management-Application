package com.andersen_intensive.hotel.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
public class Reservation {

    private int id;
    private int clientID;
    private int apartmentID;
    private List<Utility> utilities = new ArrayList<>();
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation(int id, int clientID, int apartmentID, LocalDate checkIn) {
        this.id = id;
        this.clientID = clientID;
        this.apartmentID = apartmentID;
        this.checkIn = checkIn;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", clientID=" + clientID +
                ", apartmentID=" + apartmentID +
                ", utilities=" + utilities +
                ", checkIn=" + checkIn +
                "Check-out: " + (checkOut == null ? "haven't checked out yet" : checkOut) +
                '}';
    }
}
