package com.github.T3Allam.warehouse;

import java.util.Hashtable;

public class Warehouse{
    private String name;
    private String address;
    private Coordinates coordinates;
    private Hashtable<String, Integer> stock;

    public Warehouse(){
        this.stock = new Hashtable<>();
    }

    public double[] getCoordinates() {
        return coordinates.getCoordinates(this.address);
    }

    public Hashtable<String, Integer> getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public int currentInventory(String sku) {
        if (stock.containsKey(sku)) {
            return stock.get(sku);
        }
        return 0;
    }

    public void fulfillOrder(String sku, int numInStock) {
        this.stock.replace(sku, stock.get(sku)-numInStock);
        System.out.println("Order for product sku:" + sku + " fulfilled.");
    }
}
