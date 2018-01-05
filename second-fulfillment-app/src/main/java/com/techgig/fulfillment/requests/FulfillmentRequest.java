package com.techgig.fulfillment.requests;

import com.techgig.fulfillment.data.Order;

import java.util.List;

public class FulfillmentRequest {

    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
