package com.github.T3Allam.warehouse;

import java.util.ArrayList;

public class OrderFulfillment{
    private ArrayList<Warehouse> warehouses;
    final int R = 6371;

    public OrderFulfillment() {
        this.warehouses = new ArrayList<>();
    }

    public ArrayList<Warehouse> getSortedWarehouses (Order order) {
        //get address of order
        String orderAddress = order.getAddress();
        //get coordinates of address
        double[] coordinates = order.getCoordinates(orderAddress);
        //find difference between two coordinates
        double orderLat = coordinates[0];
        double orderLon = coordinates[1];
        //Sorting warehouses according to distance from customer's address - ascending order
        ArrayList<Double> sortedDistance = new ArrayList<>();
        ArrayList<Warehouse> sortedWarehouses = new ArrayList<>();
        for (Warehouse warehouse: warehouses) {
            //Extract lat and longitude of warehouse
            double[] warehouseCoordinates = warehouse.getCoordinates();
            double warehouseLat = warehouseCoordinates[0];
            double warehouseLon = warehouseCoordinates[1];
            //Difference : longitude & lat of addreess and warehouse
            double latDifference = orderLat - warehouseLat;
            double lonDifference = orderLon - warehouseLon;
            //convert to Radians
            double latDifferenceRadians = latDifference * (Math.PI/180);
            double lonDifferenceRadians = lonDifference * (Math.PI/180);
            //Calculating distance
            Double a = Math.sin(latDifferenceRadians / 2) * Math.sin(latDifferenceRadians / 2) + Math.cos(warehouseLat * (Math.PI/180)) * Math.cos(orderLat* (Math.PI/180)) * Math.sin(lonDifferenceRadians / 2) * Math.sin(lonDifferenceRadians / 2);
            Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            Double distance = R * c;
            System.out.println(distance);
            //Populating Arraylist of sorted
            if (sortedDistance.isEmpty()) {
                sortedDistance.add(distance);
                sortedWarehouses.add(warehouse);
            } else {
                for (int i=0; i < sortedDistance.size(); i++){
                    if ( distance < sortedDistance.get(i)) {
                        sortedDistance.add(i, distance);
                        sortedWarehouses.add(i, warehouse);
                        break;
                    } else if ( distance > sortedDistance.get(sortedDistance.size()-1)) {
                        sortedDistance.add(distance);
                        sortedWarehouses.add(warehouse);
                    }
                }
            }
        }
        return sortedWarehouses;
    }

    public void fulfillOrder(Order order) {

        ArrayList<Warehouse> sortedWarehouses = getSortedWarehouses(order);

        //loop though customer order
        order.getItemList().forEach((k,v) -> {
            //check if item is in stock
            for(Warehouse warehouse: sortedWarehouses) {
                int numInStock = warehouse.currentInventory(k);
                if (numInStock == 0 ) {
                    System.out.println("Out of Stock");
                } else if (numInStock < v) {
                    System.out.println("Partial fulfillment. Amount required < amount in stock");
                    warehouse.fulfillOrder(k, v);
                } else if (numInStock > v) {
                    System.out.println("In stock.");
                    warehouse.fulfillOrder(k,v);
                }
            }
        });
    }
}
