package com.github.T3Allam.warehouse;

import java.util.Hashtable;

public class Order {

    private Hashtable<String, Integer> itemList;
    private String address;
    private Coordinates coordinates;
    private String customerName;


    public Order (String address, String name) {
        this.itemList = new Hashtable<>();
        this.address = address;
        this.customerName = name;
    }

    public Hashtable<String, Integer> getItemList() {
        return itemList;
    }
    public String getAddress(){return this.address;}
    public double[] getCoordinates(String address){return coordinates.getCoordinates(address);}
    public void addItem(Item item, Integer n) {itemList.put(item.getSku(), n);}
    public void replaceItem(Item item, Integer oldValue, Integer newValue) {itemList.replace(item.getSku(), oldValue, newValue);}
    public void removeItem(Item item) {
        itemList.remove(item.getSku());
    }
}
