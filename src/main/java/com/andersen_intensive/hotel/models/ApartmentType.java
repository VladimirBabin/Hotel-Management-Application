package com.andersen_intensive.hotel.models;
public enum ApartmentType {
    SINGLE,
    DOUBLE;

    public static ApartmentType valueOfLabel(String label) {
        for (ApartmentType apartmentType : values()) {
            if (apartmentType.ordinal() == Integer.parseInt(label) - 1) {
                return apartmentType;
            }
        }
        throw new IllegalArgumentException();
    }
}

