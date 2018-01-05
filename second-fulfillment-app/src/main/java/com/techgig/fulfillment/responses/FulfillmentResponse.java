package com.techgig.fulfillment.responses;

import com.techgig.fulfillment.data.Consignment;

import java.util.List;

public class FulfillmentResponse {

    private List<Consignment> consignments;

    public FulfillmentResponse(List<Consignment> consignments) {
        this.consignments = consignments;
    }

    public List<Consignment> getConsignments() {
        return consignments;
    }
}
