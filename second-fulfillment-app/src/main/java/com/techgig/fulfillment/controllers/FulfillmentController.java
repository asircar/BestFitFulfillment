package com.techgig.fulfillment.controllers;

import com.techgig.fulfillment.requests.FulfillmentRequest;
import com.techgig.fulfillment.responses.FulfillmentResponse;
import com.techgig.fulfillment.services.FulfillmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fulfill")
public class FulfillmentController {

    @Autowired
    private FulfillmentService fulfillmentService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody FulfillmentResponse fulfill(@RequestBody FulfillmentRequest fulfillmentRequest) {
        return new FulfillmentResponse(fulfillmentService.fulfill(fulfillmentRequest.getOrders(), fulfillmentRequest.getWarehouses()));
    }
}


