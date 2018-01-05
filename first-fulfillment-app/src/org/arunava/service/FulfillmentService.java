package org.arunava.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.arunava.data.FulfillmentData;
import org.arunava.entities.Order;
import org.arunava.entities.Warehouse;

public class FulfillmentService {
	
	public void fulfill(List<Order> orders, List<Warehouse> warehouses) {
		
		for(Order order : orders) {
			//Calculate fitness of warehouses per order
			calculateFitness(order, warehouses);
			
			//Sorting based on best fit warehouse
			Collections.sort(warehouses, (o1, o2) -> o2.getPercentFulfilled().compareTo(o1.getPercentFulfilled()));
			
			//Printing the fitness of each warehouse
			//System.out.println("-- Fitness of the various warehouses for the order ---");
			//for(Warehouse warehouse : warehouses) {				
			//	System.out.println(order.getDestination() + " --- " + warehouse.getLocation() + " --- " + warehouse.getPercentFulfilled());
			//}
			
			//Fill the orders
			List<FulfillmentData> fulfillmentDataList = fillOrder(order, warehouses);		
			
			//Print the output
			//System.out.println("--- Output ---");
			for(FulfillmentData fulfillmentData : fulfillmentDataList) {				
				System.out.println(fulfillmentData.getLocation() + " --- " + fulfillmentData.getDestination() + " --- " 
						+ fulfillmentData.getSku() + " --- " + fulfillmentData.getAmount());
			}
		}
		
	}

	private List<FulfillmentData> fillOrder(Order order, List<Warehouse> warehouses) {
		List<FulfillmentData> fulfillmentDataList = new ArrayList<>();
		Map<String, Double> orderEntries = order.getOrderEntries();
		for(Warehouse warehouse : warehouses) {
			Map<String, Double> inventory = warehouse.getInventory();
			
			for (Map.Entry<String, Double> orderEntry : orderEntries.entrySet()) {
				Double amountFullfilled = 0.0;
				String sku = orderEntry.getKey();
				Double orderedQty = orderEntry.getValue();
				
				if(orderedQty == 0.0) {
					continue;
				}
				
				if(inventory.get(sku) != null) {
				
					Double qtyInInventory = inventory.get(sku);
					
					//Reduce the quantity in order and calculate the amountFulfilled
					if(orderedQty <= qtyInInventory) {
						orderEntry.setValue(0.0);
						amountFullfilled = orderedQty;
					} else {
						orderEntry.setValue(orderedQty - qtyInInventory);
						amountFullfilled = qtyInInventory;
					}
					
					//Reduce the inventory quantity
					qtyInInventory = qtyInInventory - orderedQty;
					inventory.put(sku, qtyInInventory);
						
					//Populate the output data structure
					FulfillmentData fulfillmentData = new FulfillmentData();
					fulfillmentData.setLocation(warehouse.getLocation());
					fulfillmentData.setDestination(order.getDestination());
					fulfillmentData.setSku(sku);
					fulfillmentData.setAmount(amountFullfilled);
					
					fulfillmentDataList.add(fulfillmentData);
				}
				
			}
			
		}
		return fulfillmentDataList;
	}

	public void calculateFitness(Order order, List<Warehouse> warehouses) {
		for(Warehouse warehouse : warehouses) {
			double percentFulfilled = calculateFitnessOfWarehouseForOrder(order, warehouse);
			warehouse.setPercentFulfilled(percentFulfilled);
		}		
	}
	
	private double calculateFitnessOfWarehouseForOrder(Order order, Warehouse warehouse) {
		Map<String, Double> orderEntries = order.getOrderEntries();
		Map<String, Double> inventory = warehouse.getInventory();
		
		double quantityNotFulfilled = 0;
		double quantityOfAllItemsInOrder = 0;
		double percentFulfilled = 0;
		
		for(Map.Entry<String, Double> entryForOrderEntries : orderEntries.entrySet()) {
			double quantityInOrder = entryForOrderEntries.getValue();
			double quantityInWarehouse = inventory.get(entryForOrderEntries.getKey()) == null ? 0 : inventory.get(entryForOrderEntries.getKey());
			
			quantityOfAllItemsInOrder = quantityOfAllItemsInOrder + quantityInOrder;
			
			if(quantityInWarehouse < quantityInOrder) {
				quantityNotFulfilled = quantityNotFulfilled + (quantityInOrder - quantityInWarehouse);
			}			
		}
		
		if(quantityNotFulfilled > 0) {
			percentFulfilled = (quantityOfAllItemsInOrder - quantityNotFulfilled)/quantityOfAllItemsInOrder;
		} else {
			percentFulfilled = 1;
		}
		return percentFulfilled;
	}	
}
