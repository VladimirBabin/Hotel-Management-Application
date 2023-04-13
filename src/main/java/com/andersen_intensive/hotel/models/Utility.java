package com.andersen_intensive.hotel.models;

import java.math.BigDecimal;

public class Utility {
    private int id;
    private String name;
    private BigDecimal price;

    public Utility(int id, String name, BigDecimal price) {
        this.id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
