package com.andersen_intensive.hotel.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Client {

    private String firstName;
    private String lastName;
    private int personalID;
    private String phoneNumber;

    public Client(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + "\n" +
                "LastName: " + lastName + "\n" +
                "PersonalID: " + personalID + "\n" +
                "Contact Number: " + phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Client other) || obj == null) {
            return false;
        }
        return this.firstName.equals(other.getFirstName()) && this.lastName.equals(other.getLastName())
                && Objects.equals(this.personalID, other.getPersonalID()) && Objects.equals(this.phoneNumber, other.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() + lastName.hashCode();
    }
}
