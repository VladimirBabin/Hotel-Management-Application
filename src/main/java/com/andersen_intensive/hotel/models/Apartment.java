package com.andersen_intensive.hotel.models;

import java.util.Objects;

public class Apartment implements IApartment {
    private int apartmentNumber;
    private Double price;
    private ApartmentType apartmentType;
    private ApartmentStatus apartmentStatus;

    public Apartment(int apartmentNumber, Double price, ApartmentType apartmentType) {
        this.apartmentNumber = apartmentNumber;
        this.price = price;
        this.apartmentType = apartmentType;
        this.apartmentStatus = ApartmentStatus.AVAILABLE;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public Double getApartmentPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public ApartmentStatus getApartmentStatus() {
        return apartmentStatus;
    }

    public void setApartmentStatus(ApartmentStatus apartmentStatus) {
        this.apartmentStatus = apartmentStatus;
    }

    @Override
    public String toString() {
        return "Apartment number" +
                apartmentNumber + "\n" +
                "price: $" + price + "\n" +
                "type apartment: " + apartmentType + "\n" +
                "status: " + apartmentStatus + "\n" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return apartmentNumber == apartment.apartmentNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(apartmentNumber);
    }
}
