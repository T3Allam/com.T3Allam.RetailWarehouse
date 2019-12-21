package com.github.T3Allam.warehouse;


public class Item {
    String name;
    String sku;
    double price;

    private Item(String name, String sku, double price){
        this.name = name;
        this.sku = sku;
        this.price = price;
    }
}
