package com.andersen_intensive.hotel.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Apartment {

    private final int apartmentId;
    private BigDecimal price;
    private final ApartmentType apartmentType;
    private ApartmentStatus apartmentStatus;

    public Apartment(int apartmentId, BigDecimal price, ApartmentType apartmentType) {
        this.apartmentId = apartmentId;
        this.price = price;
        this.apartmentType = apartmentType;
        this.apartmentStatus = ApartmentStatus.AVAILABLE;
    }

    public Apartment(int apartmentId, BigDecimal price, String apartmentType, String apartmentStatus) {
        this.apartmentId = apartmentId;
        this.price = price;
        this.apartmentType = ApartmentType.valueOf(apartmentType);
        this.apartmentStatus = ApartmentStatus.valueOf(apartmentStatus);
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public BigDecimal getApartmentPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public ApartmentStatus getApartmentStatus() {
        return apartmentStatus;
    }

    public void setApartmentStatus(ApartmentStatus apartmentStatus) {
        this.apartmentStatus = apartmentStatus;
    }

    @Override
    public String toString() {
        return "Apartment number " +
                apartmentId + "\n" +
                "price: $" + price + "\n" +
                "type apartment: " + apartmentType + "\n" +
                "status: " + apartmentStatus + "\n";
    }

    public String toStringList() {
        return "Apartment number " +
                apartmentId +
                " price: $" + price +
                " type apartment: " + apartmentType +
                " status: " + apartmentStatus + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return apartmentId == apartment.apartmentId && Objects.equals(price, apartment.price) && apartmentType ==
                apartment.apartmentType && apartmentStatus == apartment.apartmentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(apartmentId);
    }
}