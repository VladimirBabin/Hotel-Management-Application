package com.andersen_intensive.hotel.models;

public enum ApartmentStatus {
    AVAILABLE("1"),
    OCCUPIED("2"),
    UNAVAILABLE("3");

    public final String label;

    ApartmentStatus(String label) {
        this.label = label;
    }

    public static ApartmentStatus valueOfLabel(String label) {
        for (ApartmentStatus apartmentStatus : values()) {
            if (apartmentStatus.label.equals(label)) {
                return apartmentStatus;
            }
        }
        throw new IllegalArgumentException();
    }
}
