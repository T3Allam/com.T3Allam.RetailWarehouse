package com.github.T3Allam.warehouse;

import java.util.Hashtable;

public class Order {

    private Hashtable<Item, Integer> itemList;
    private String address;
    private Coordinates coordinates;
    private String customerName;


    public Order (String address, String name) {
        this.itemList = new Hashtable<>();
        this.coordinates = new Coordinates();
        this.address = address;
        this.customerName = name;
    }

    public Hashtable<Item, Integer> getItemList() {
        return this.itemList;
    }

    public String getAddress(){
        return this.address;
    }

    public double[] getCoordinates(){
        return coordinates.getCoordinates(this.address);
    }

    public void addItem(Item item, Integer n) {
        this.itemList.put(item, n);
    }
    public void replaceItem(Item item, Integer newValue) {
        this.itemList.replace(item, newValue);
    }
    public void removeItem(Item item) {
        this.itemList.remove(item);
    }
}
