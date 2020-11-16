package com.github.T3Allam.warehouse.subjectclass;

import com.github.T3Allam.warehouse.Item;

import java.util.Hashtable;

public class Warehouse {
    private String name;
    private static String address;
    private Hashtable<Item, Integer> stock;

    public Warehouse(String name, String address){
        this.stock = new Hashtable<>();
        this.name = name;
        Warehouse.address = address;
    }

    public String getAddress() {
        return address;
    }

    public Hashtable<Item, Integer> getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public void updateStock(Item item, int stock) {
        int updatedStock = this.getStock().get(item) + stock;
        this.getStock().replace(item, updatedStock);
    }

    public int currentInventory(Item item) {
        return this.getStock().getOrDefault(item, 0);
    }

    public void fulfillOrder(Item item, int stock) { ;
        System.out.println("Product: " + item.getName() + " " + "Quantity: " + stock + " shipped.");
        //Updating stock - passing in negative number to indicate that purchase has been made and inventory reduced
        updateStock(item, -stock);
    }

    public void displayStock() {
        stock.forEach((item, quantity) -> System.out.println("Item : " + item.getName() + ", Quantity : " + quantity));
    }

}
