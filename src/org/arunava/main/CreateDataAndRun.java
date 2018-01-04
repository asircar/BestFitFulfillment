package org.arunava.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.arunava.entities.Order;
import org.arunava.entities.Warehouse;
import org.arunava.service.FulfillmentService;

public class CreateDataAndRun {

	public static void main(String[] args) {
		
		//Data for 1st Warehouse
		Warehouse warehouse1 = new Warehouse();
		warehouse1.setLocation("Goa");
		
		Map<String,Double> inventory1 = new HashMap<String, Double>();
		inventory1.put("product-1", 10.0);
		inventory1.put("product-2", 12.0);
		inventory1.put("product-3", 5.0);
		
		warehouse1.setInventory(inventory1);
		
		//Data for 2nd Warehouse
		Warehouse warehouse2 = new Warehouse();
		warehouse2.setLocation("Mumbai");
				
		Map<String,Double> inventory2 = new HashMap<String, Double>();
		inventory2.put("product-1", 4.0);
		inventory2.put("product-2", 4.0);
		inventory2.put("product-3", 4.0);
				
		warehouse2.setInventory(inventory2);
				
		//Data for 3rd Warehouse
		Warehouse warehouse3 = new Warehouse();
		warehouse3.setLocation("Moradabad");
				
		Map<String,Double> inventory3 = new HashMap<String, Double>();
		inventory3.put("product-1", 4.0);
		inventory3.put("product-4", 5.0);
				
		warehouse3.setInventory(inventory3);
		
		//Prepare the warehouses list
		List<Warehouse> warehouses = new ArrayList<>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		warehouses.add(warehouse3);
		
		//Prepare Order 1 Data
		Order order1 = new Order();
		order1.setDestination("Surat");
		
		Map<String, Double> orderEntries1 = new HashMap<String, Double>();
		orderEntries1.put("product-1", 14.0);
		orderEntries1.put("product-2", 10.0);
		orderEntries1.put("product-3", 3.0);
		
		order1.setOrderEntries(orderEntries1);
		
		//Prepare Order 2 Data
		Order order2 = new Order();
		order2.setDestination("Noida");
		
		Map<String, Double> orderEntries2 = new HashMap<String, Double>();
		orderEntries2.put("product-2", 6.0);
		orderEntries2.put("product-3", 1.0);
		orderEntries2.put("product-4", 5.0);
		
		order2.setOrderEntries(orderEntries2);
		
		//Prepare the orders list
		List<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);
		
		//Call the fulfillment service and pass on the warehouses and orders
		FulfillmentService fulfillmentService = new FulfillmentService();
		fulfillmentService.fulfill(orders, warehouses);
				
	}

}
