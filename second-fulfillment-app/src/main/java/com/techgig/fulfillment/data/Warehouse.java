package com.techgig.fulfillment.data;

import java.util.Map;

public class Warehouse {

    private String location;

    private Map <String, Double> inventory;

    private Double percentFulfilled;

    public Warehouse(String location, Map<String, Double> inventory, Double percentFulfilled) {
        this.location = location;
        this.inventory = inventory;
        this.percentFulfilled = percentFulfilled;
    }

    public String getLocation() {
        return location;
    }

    public Map<String, Double> getInventory() {
        return inventory;
    }

    public Double getPercentFulfilled() {
        return percentFulfilled;
    }

    public void setPercentFulfilled(Double percentFulfilled) {
        this.percentFulfilled = percentFulfilled;
    }
}
