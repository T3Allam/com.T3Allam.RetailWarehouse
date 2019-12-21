package com.github.T3Allam.warehouse;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Order {
    private Hashtable orderList;
    private String address;
    private String name;

    public Order (String address, String name) {
        this.orderList = new Hashtable<Item, Integer>();
        this.address = address;
        this.name = name;
    }

    public void addItem(Item item, Integer n) {
        orderList.put(item, n);
    }

    public void replaceItem(Item item, Integer oldValue, Integer newValue) {
        orderList.replace(item, oldValue, newValue);
    }
    public void removeItem(Item item) {
        orderList.remove(item);
    }
}
