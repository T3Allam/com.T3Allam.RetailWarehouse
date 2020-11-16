package com.github.T3Allam.warehouse.proxy;

import com.github.T3Allam.warehouse.Coordinates;
import com.github.T3Allam.warehouse.subjectclass.Warehouse;
import com.github.T3Allam.warehouse.orders.Order;

import java.util.*;

 /*
    This class is a proxy class - checks inventory in real subject class (warehouse) before it sends the order
    An order comes in with an address
    Adds all warehouses within the geographical area
    Warehouses are sorted based on distance from order's address
    Partial orders are allowed e.g Order requires 5 keyboards. If closest warehouse has 3 keyboards then partial order of 3 keyboards will be delivered. Remaining two will searched for in next warehouses
    I understand there are complexities in a real world application such as competition for shared resources (items in this case) and necessity for thread safety
  */

public class OrderFulfillment {
    private List<Warehouse> warehouses;
    private Map<Warehouse, Double> warehousesDistances;
    private Coordinates coordinates;
    //earth radius - we need this to calculate distances
    final int R = 6371;
    Order order;

    public OrderFulfillment(Order order) {
        this.warehouses = new ArrayList<>(); //to add all warehouses within an area
        this.warehousesDistances = new HashMap<>(); //to map each warehouse to distance from order address
        this.coordinates = new Coordinates();
        this.order = order;
    }

    public void addWarehouse(Warehouse warehouse) {
        this.warehouses.add(warehouse);
    }

    //populating value (distance from order address) in warehouses
    private void populateDistance() {
        for (Warehouse warehouse : warehouses) {
//            System.out.println(warehouse.getName() + "  " + warehouse.getAddress()+ "   " + order.getAddress());
            //Calculating distance between warehouse and order's address
            double distance = getDistance(coordinates.getCoordinates(order.getAddress()), coordinates.getCoordinates(warehouse.getAddress()));
            System.out.println();
            warehousesDistances.put(warehouse, distance);
        }
    }

    //sort hashmap of warehouse, distance [Source of code for this method: geeksforgeeks.org]
    private HashMap<Warehouse, Double> getSortedWarehouseDistance () {
        List<Map.Entry<Warehouse, Double> > list = new LinkedList<>(warehousesDistances.entrySet());
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));
        HashMap<Warehouse, Double> temp = new LinkedHashMap<>();
        for (Map.Entry<Warehouse, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    private double getDistance(double[] orderCoordinates, double[] warehouseCoordinates) {
        //Difference : longitude & lat of addreess and warehouse
        double latDifference = orderCoordinates[0] - warehouseCoordinates[0];
        double lonDifference = orderCoordinates[1] - warehouseCoordinates[1];
        //convert to Radians
        double latDifferenceRadians = latDifference * (Math.PI/180);
        double lonDifferenceRadians = lonDifference * (Math.PI/180);
        //Calculating distance
        double a = Math.sin(latDifferenceRadians / 2) * Math.sin(latDifferenceRadians / 2) + Math.cos(warehouseCoordinates[0] * (Math.PI/180)) * Math.cos(orderCoordinates[0]* (Math.PI/180)) * Math.sin(lonDifferenceRadians / 2) * Math.sin(lonDifferenceRadians / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = R * c;
        return distance;
    }

    public void fulfillOrder( ) {
        populateDistance();
        HashMap<Warehouse, Double> sortedWarehouseDistances= getSortedWarehouseDistance();
        //loop though customer order
        order.getItemList().forEach((item,quantity) -> {
            //traversing through warehouse - warehouse with closest proximity first
            for(Map.Entry<Warehouse, Double> m: sortedWarehouseDistances.entrySet()) {
                System.out.println("Quantity: " + quantity);
                //exit loop if order fullfilled for item
                if (quantity==0) {
                    break;
                }
                int numInStock=0;
                //checking stock of current item - corner case where warehouse does not have this item registered
                try {
                    numInStock = m.getKey().currentInventory(item);
                } catch (Exception e ) {
                    System.out.println("This warehouse does not have this item registered in its stock");
                }
                //case 1: item not in stock
                if (numInStock == 0 ) {
                    System.out.println("Order: " + order+ "  " + item.getName() + " is out of stock in warehouse: " + m.getKey().getName() + " or item not registered at warehouse");
                    continue;
                    //case 2: quantity required larger than what is in stock - partial fulfillment
                } else if (numInStock < quantity) {
                    System.out.println("Order ID: " + order.getId()+ "  " + "Partial fulfillment from warehouse: " + m.getKey().getName());
                    m.getKey().fulfillOrder(item, numInStock);
                    //updating what remains of partially fulfilled order for this type of item
                    quantity = quantity - numInStock;
                    System.out.println("Updated invesntory " + m.getKey().currentInventory(item));
                    System.out.println("Remaining q " + quantity);
                    continue;
                    //case 3: warehouse can fullfil this order completely
                } else if (numInStock >= quantity) {
                    System.out.println("Order ID: " + order.getId()+ "  " + "In stock. Fulfilled from warehouse: " +  m.getKey().getName());
                    m.getKey().fulfillOrder(item, quantity);
                    System.out.println("Updated invesntory " + m.getKey().currentInventory(item));
                    quantity=0;
                    System.out.print("Remaining q " + quantity);
                    break;
                }
            }
            if (quantity>0)
                System.out.println("Not enough stock to fulfill order of product " + item.getName() + ". Quantity unfulfilled: " + quantity);
        });
    }
}
