# com.T3Allam.RetailWarehouse

Proxy Pattern - Warehouse & Orderfulfillment
Client places order
Warehouses are sorted according to proximity to order address
Proxy class - Orderfulfillment - checks inventory in real subject class - warehouse
If in stock then item is shipped and stock is updated
