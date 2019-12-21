package com.github.T3Allam.warehouse;

//Singleton Design Pattern
public class Item {
    private static String name;
    private static String sku;
    private static double price;
    private static Item uniqueInstance = null;

    private Item(String name, String sku, double price){
        this.name = name;
        this.sku = sku;
        this.price = price;
    }

    public static Item getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Item(Item.name, Item.sku, Item.price);
        }
        return uniqueInstance;
    }
}
