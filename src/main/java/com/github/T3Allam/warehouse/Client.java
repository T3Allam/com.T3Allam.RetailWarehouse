package com.github.T3Allam.warehouse;

import java.util.Arrays;

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

        //Testing
//        System.out.println(w3.currentInventory(subaru));
        /* ********************************** */

        //Create an order with 3 items - Order Class
        String address = "685 Markham St, Toronto, ON M6G2M2";
        String name = "reprisk";
        Order o1 = new Order(address, name);
//        System.out.println("Product: " + bmw + " " + bmw.getName());
//        System.out.println("Product: " + benz + " " + benz.getName());
//        System.out.println("Product: " + audi + " " + audi.getName());
//        System.out.println("Product: " + tesla + " " + tesla.getName());
//        System.out.println("Product: " + subaru + " " + subaru.getName());

        o1.addItem(bmw, 2);
        o1.addItem(benz,3);
        o1.replaceItem(bmw, 4);
        o1.addItem(tesla, 4);
//        System.out.println(o1.getItemList().toString());
//        System.out.println(Arrays.toString(o1.getCoordinates()));

        //Orderfulfillment
        OrderFulfillment newOrder = new OrderFulfillment();
        newOrder.addWarehouse(w1);
        newOrder.addWarehouse(w2);
        newOrder.addWarehouse(w3);
        newOrder.addWarehouse(w4);
        newOrder.addWarehouse(w5);

        newOrder.getSortedWarehouses(o1);

    }
}
