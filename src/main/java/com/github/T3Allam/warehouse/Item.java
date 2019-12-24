package com.github.T3Allam.warehouse;


public class Item {
    String name;
    String sku;
    double price;

    public Item(String name, String sku, double price){
        this.name = name;
        this.sku = sku;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }
}
