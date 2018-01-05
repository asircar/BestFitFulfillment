package com.techgig.fulfillment.requests;

import com.techgig.fulfillment.data.Order;
import com.techgig.fulfillment.data.Warehouse;

import java.util.List;

public class FulfillmentRequest {

    private List<Order> orders;
    private List<Warehouse> warehouses;

    public List<Order> getOrders() {
        return orders;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

}
