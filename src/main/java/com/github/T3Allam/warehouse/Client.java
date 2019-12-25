package com.github.T3Allam.warehouse;

import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) {
        //Create 5 Items - Item Class
        Item bmw = new Item("bmw", "1",5.22);
        Item benz = new Item("benz", "2", 6.44);
        Item audi = new Item("audi", "3", 4.74);
        Item tesla = new Item("tesla", "4", 5.34);
        Item subaru = new Item("subaru", "5", 4.34);
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

        //Create an order with 3 items - Order Class
        String address = "792 Bathurst St, Toronto, ON M6G2M2";
        String name = "Ziggy";
        Order o1 = new Order(address, name);
        o1.addItem(benz,3);
        o1.addItem(bmw, 4);
        o1.addItem(tesla, 4);


        //Orderfulfillment
        //Order 1
        OrderFulfillment newOrder = new OrderFulfillment();
        newOrder.addWarehouse(w1);
        newOrder.addWarehouse(w2);
        newOrder.addWarehouse(w3);
        newOrder.addWarehouse(w4);
        newOrder.addWarehouse(w5);
        newOrder.fulfillOrder(o1);
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Order 2
        Order o2 = new Order("10 Lower Jarvis St, Toronto, ON M5E 1Z2", "Starbucks");
        o2.addItem(audi,3);
        o2.addItem(bmw, 4);
        o2.addItem(subaru, 4);
        o2.addItem(tesla, 4);
        OrderFulfillment newOrder2 = new OrderFulfillment();
        newOrder2.addWarehouse(w1);
        newOrder2.addWarehouse(w2);
        newOrder2.addWarehouse(w3);
        newOrder2.addWarehouse(w4);
        newOrder2.addWarehouse(w5);
        newOrder2.fulfillOrder(o2);
    }
}
