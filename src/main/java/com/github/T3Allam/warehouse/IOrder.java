package com.github.T3Allam.warehouse;

public interface IOrder {
    void fulfillOrder(Order order);
    void addWarehouse(Warehouse warehouse);
}