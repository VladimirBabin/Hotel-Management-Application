package com.andersen_intensive.hotel.models;

public class Apartment {
    private int apartmentNumber;
    private Double price;
    private ApartmentType apartmentType;
    private ApartmentStatus apartmentStatus;

    public Apartment(int apartmentNumber, Double price, ApartmentType apartmentType, ApartmentStatus apartmentStatus) {
        this.apartmentNumber = apartmentNumber;
        this.price = price;
        this.apartmentType = apartmentType;
        this.apartmentStatus = apartmentStatus;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public Double getPrice() {
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
                apartmentNumber +
                ", price: $" + price +
                ", type apartment: " + apartmentType +
                ", status: " + apartmentStatus ;
    }

}
