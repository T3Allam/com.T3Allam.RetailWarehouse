package com.github.T3Allam.warehouse.orders;

import com.github.T3Allam.warehouse.Item;
import java.util.Hashtable;

public class Order {

    private Hashtable<Item, Integer> itemList;
    private String address;
    private String customerName;


    public Order (String address, String name) {
        this.itemList = new Hashtable<>();
        this.address = address;
        this.customerName = name;
    }

    public Hashtable<Item, Integer> getItemList() {
        return this.itemList;
    }

    public String getAddress(){
        return this.address;
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
