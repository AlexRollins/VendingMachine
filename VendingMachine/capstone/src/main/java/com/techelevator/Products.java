package com.techelevator;

import java.io.File;

public class Products {
    private String Slot;
    private String name;
    private String type;
    private Double price;
    private Integer itemCount = 5;

    //Constructors
    public Products(String name, Double price, String type){
        this.name = name;
        this.price = price;
        this.type = type;
    }
    public Products(){

    }
    //Methods

    //Getters

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
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

    public void setPrice(Double price) {
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