package com.github.T3Allam.warehouse;


import com.github.T3Allam.warehouse.helpers.NumberGenerator;

public class Item {
    private String name;
    private int id;
    private double price;

    public Item(String name){
        this.name = name;
        this.id = NumberGenerator.generateUniqueItemId();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return this.id;
    }
}
