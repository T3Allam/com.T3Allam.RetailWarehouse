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

    public void fulfillOrder(Item k, int v) { ;
        System.out.println(v + " of the product " + k.getName() + " have been shipped.");
    }
}
