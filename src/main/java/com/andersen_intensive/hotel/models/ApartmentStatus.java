package com.andersen_intensive.hotel.models;
public enum ApartmentStatus {
    AVAILABLE,
    OCCUPIED,
    UNAVAILABLE;

    public static ApartmentStatus fromValue(int value) {
        for (ApartmentStatus status : values()) {
            if (status.ordinal() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}
