package com.andersen_intensive.hotel.models;

import java.math.BigDecimal;

public class Utility {
    private static int count = 0;
    private int id;
    private String name;
    private int price;

    public Utility(String name, int price) {
        this.id = ++count;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Service: " +
                "ID: " + id +
                "Name: '" + name + '\'' +
                "Price: " + price;
    }
}
