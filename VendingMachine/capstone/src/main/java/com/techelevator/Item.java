package com.techelevator;

import java.io.File;
import java.math.BigDecimal;

public class Item {
    private String Slot;
    private String name;
    private String type;
    private BigDecimal price;
    private int itemCount = 5;

    //Constructors
    public Item(String name, BigDecimal price, String type){
        this.name = name;
        this.price = price;
        this.type = type;
    }
    public Item(){

    }
    //Methods

    //Getters

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    //Setters

    public void setType(String type) {
        this.type = type;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlot() {
        return Slot;
    }

    public void setSlot(String slot) {
        Slot = slot;
    }
}