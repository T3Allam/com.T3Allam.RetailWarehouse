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
    private Map<Double, Warehouse> distanceWarehouse;
    List<Warehouse> warehouses;
    Coordinates coordinates;
    //earth raduis - we need this to calculate distances
    final int R = 6371;
    Order order;

    public OrderFulfillment(Order order) {
        this.warehouses = new ArrayList<>(); //to add all warehouses within an area
        this.distanceWarehouse = new TreeMap<>(); //to hold sorted warehouses
        this.coordinates = new Coordinates();
        this.order = order;
    }

    public void addWarehouse(Warehouse warehouse) {
        this.warehouses.add(warehouse);
    }

    //populating value (distance from order address) in warehouses
    private void populateDistance() {
        //get coordinates of address
        double[] orderCoordinates = coordinates.getCoordinates(order.getAddress());
        for (Warehouse warehouse: warehouses) {
            //Get coordinates of warehouse
            double[] warehouseCoordinates = coordinates.getCoordinates(warehouse.getAddress());
            //Calculating distance between warehouse and order's address
            double distance = getDistance(orderCoordinates, warehouseCoordinates);
            //populating treemap
            distanceWarehouse.put(distance, warehouse);
        }
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
        //loop though customer order
        order.getItemList().forEach((item,quantity) -> {
            //traversing through warehouse - warehouse with closest proximity first
            for(Map.Entry<Double, Warehouse> m: distanceWarehouse.entrySet()) {
                //exit loop if order fullfilled for item
                if (quantity==0) {
                    break;
                }
                //checking stock of current item
                int numInStock = m.getValue().currentInventory(item);
                //case 1: item not in stock
                if (numInStock == 0 ) {
                    System.out.println("Order Address: " + order.getAddress()+ "  " + item.getName() + " is out of stock in warehouse: " + m.getValue().getName());
                    continue;
                    //case 2: quantity required larger than what is in stock - partial fulfillment
                } else if (numInStock < quantity) {
                    System.out.println("Order Address: " + order.getAddress()+ "  " + "Partial fulfillment from warehouse: " + m.getValue().getName());
                    m.getValue().fulfillOrder(item, numInStock);
                    m.getValue().updateStock(item, -numInStock);
                    //updating what remains of partially fulfilled order for this type of item
                    quantity = quantity - numInStock;
                    continue;
                    //case 3: warehouse can fullfil this order completely
                } else if (numInStock >= quantity) {
                    System.out.println("Order Addrss: " + order.getAddress()+ "  " + "In stock. Fulfilled from warehouse: " +  m.getValue().getName());
                    m.getValue().fulfillOrder(item, numInStock);
                    m.getValue().updateStock(item, -numInStock);
                    //updating quantity
                    quantity = 0;
                    continue;
                }
            }
            //if order has not been completely filled
            if (quantity>0)
                System.out.println("Order Address: " + order.getAddress()+ "  " + "Not enough stock to fulfill order of product " + item.getName() + ". Quantity unfulfilled: " + quantity);
            System.out.println("********************************************************");
        });
    }


}
