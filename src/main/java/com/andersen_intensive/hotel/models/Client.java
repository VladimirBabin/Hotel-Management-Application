package com.andersen_intensive.hotel.models;

public class Client {
    private String firstName;
    private String lastName;
    private int personalID;
    private int phoneNumber;

    public Client(String firstName, String lastName, int personalID, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalID = personalID;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPersonalID() {
        return personalID;
    }

    public void setPersonalID(int personalID) {
        this.personalID = personalID;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
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
                && this.personalID == (other.getPersonalID()) && this.phoneNumber == (other.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return firstName.hashCode() + lastName.hashCode();
    }
}
