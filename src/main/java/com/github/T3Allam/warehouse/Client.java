package com.github.T3Allam.warehouse;

import com.github.T3Allam.warehouse.orders.Order;
import com.github.T3Allam.warehouse.proxy.OrderFulfillment;
import com.github.T3Allam.warehouse.subjectclass.Warehouse;

public class Client {

    public static void main(String[] args) {

        //Create 5 Items - Item Class
        Item bmw = new Item("bmw");
        Item benz = new Item("benz");
        Item audi = new Item("audi");
        Item tesla = new Item("tesla");
        Item subaru = new Item("subaru");
        //Create 6 Warehouses & stocking up - Warehouse Class
        Warehouse w1 = new Warehouse("Toronto Christmas Market", "373 Front St E, Toronto, ON M5A 1G4");
        w1.getStock().put(bmw, 4);
        w1.getStock().put(benz, 2);
        w1.getStock().put(audi, 3);
        w1.getStock().put(tesla, 1);
        w1.getStock().put(subaru, 5);

        Warehouse w2 = new Warehouse("Nathan Phillips Square", "100 Queen St W, Toronto, ON M5H 2N1");
        w2.getStock().put(bmw, 4);
        w2.getStock().put(benz, 2);
        w2.getStock().put(audi, 3);
        w2.getStock().put(tesla, 1);
        w2.getStock().put(subaru, 5);

        Warehouse w3 = new Warehouse("Ossington", "879 Bloor St W, Toronto, ON M6G 1M4");
        w3.getStock().put(bmw, 3);
        w3.getStock().put(subaru, 2);

        Warehouse w4 = new Warehouse("Trinity Bellwoords", "894 Queen St W, Toronto, ON M6J 1G3");
        w4.getStock().put(benz, 1);
        w4.getStock().put(tesla, 5);

        Warehouse w5 = new Warehouse("Rosedale", "10 Elm Ave, Toronto, ON M4W 1N4");
        w5.getStock().put(benz, 2);
        w5.getStock().put(audi, 3);

        //Creating Order
        Order o1 = new Order("10 Toronto St, Toronto, ON, M5C 2B7", "Ziggy");
        o1.addItem(benz,25);
        o1.addItem(bmw, 7);

        //Fulfilling Order
        OrderFulfillment orderFulfillment = new OrderFulfillment(o1);
        //adding warehouse to order fulfillment
        orderFulfillment.addWarehouse(w1);
        orderFulfillment.addWarehouse(w2);
        orderFulfillment.addWarehouse(w3);
        orderFulfillment.addWarehouse(w4);
        orderFulfillment.addWarehouse(w5);

        orderFulfillment.fulfillOrder();
    }
}
