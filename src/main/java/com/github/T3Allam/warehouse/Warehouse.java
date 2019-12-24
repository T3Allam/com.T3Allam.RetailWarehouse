package com.github.T3Allam.warehouse;

import java.util.Hashtable;

public class Warehouse{
    private String name;
    private String address;
    private Coordinates coordinates;
    private Hashtable<Item, Integer> stock;

    public Warehouse(String name, String address){
        this.stock = new Hashtable<>();
        this.coordinates = new Coordinates();
        this.name = name;
        this.address = address;
    }

    public double[] getCoordinates() {
        return coordinates.getCoordinates(this.address);
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

    public int currentInventory(Item item) {
        if (this.getStock().containsKey(item)) {
            return this.getStock().get(item);
        }
        return 0;
    }

    public void fulfillOrder(Item k, int v) {
        int currentInventory = this.getStock().get(k);
        this.getStock().replace(k, currentInventory -v);
        System.out.println("Order for product sku:" + this.getStock().get(k.getSku()) + " and has name " + this.getStock().get(k.getName())
                + " has been fulfilled.");
    }
}
