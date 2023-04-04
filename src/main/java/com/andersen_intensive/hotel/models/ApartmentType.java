package com.andersen_intensive.hotel.models;

public enum ApartmentType {
    SINGLE("1"),
    DOUBLE("2");

    public final String label;

    ApartmentType(String label) {
        this.label = label;
    }

    public static ApartmentType valueOfLabel(String label) {
        for (ApartmentType apartmentType : values()) {
            if (apartmentType.label.equals(label)) {
                return apartmentType;
            }
        }
        throw new IllegalArgumentException();
    }
}
