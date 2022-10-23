package com.watsmeow.VendingMachine.dto;

import java.math.BigDecimal;

/*
* This is the data transfer object. It establishes:
* - the properties and methods that vending machine items have
* - the properties will be set to private because they should not be directly accessible
* - the methods will be set to public since we can use setters and getters to retrieve or update them
* */
public class Item {

    private String name;

    private BigDecimal cost;

    private int quantity;

    public Item(String name, BigDecimal cost, int quantity) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
