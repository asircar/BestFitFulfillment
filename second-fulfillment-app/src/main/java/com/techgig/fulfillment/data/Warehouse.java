package com.techgig.fulfillment.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Warehouse {

    private String location;

    private Map <String, Double> inventory;

    private Double percentFulfilled;

    public Warehouse() {
        super();
    }

    public Warehouse(String location, Map<String, Double> inventory) {
        this.location = location;
        this.inventory = inventory;
        this.percentFulfilled = 0D;
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
