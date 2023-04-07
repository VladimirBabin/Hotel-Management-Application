package com.andersen_intensive.hotel.models;


import java.math.BigDecimal;

public interface IApartment {
    public int getApartmentNumber();
    public BigDecimal getApartmentPrice();
    public ApartmentType getApartmentType();
    public ApartmentStatus getApartmentStatus();

}
