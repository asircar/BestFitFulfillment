package com.techgig.fulfillment.data;

public class Consignment {
    private String location;

    private String destination;

    private String sku;

    private Double amount;

    public Consignment(String location, String destination, String sku, Double amount) {
        this.location = location;
        this.destination = destination;
        this.sku = sku;
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public String getDestination() {
        return destination;
    }

    public String getSku() {
        return sku;
    }

    public Double getAmount() {
        return amount;
    }
}
