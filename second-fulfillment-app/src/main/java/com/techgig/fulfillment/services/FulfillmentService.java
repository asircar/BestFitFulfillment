package com.techgig.fulfillment.services;

import com.techgig.fulfillment.data.Consignment;
import com.techgig.fulfillment.data.Order;
import com.techgig.fulfillment.data.Warehouse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class FulfillmentService {
	
	public List<Consignment> fulfill(List<Order> orders, List<Warehouse> warehouses) {
		List<Consignment> fulfillmentDataList = new ArrayList<>();
		for (Order order : orders) {
			//Calculate fitness of warehouses per order
			calculateFitness(order, warehouses);

			//Sorting based on best fit warehouse
			Collections.sort(warehouses, (o1, o2) -> o2.getPercentFulfilled().compareTo(o1.getPercentFulfilled()));

			//Fill the orders
			fulfillmentDataList.addAll(fillOrder(order, warehouses));
		}
		return fulfillmentDataList;
	}

	private List<Consignment> fillOrder(Order order, List<Warehouse> warehouses) {
		List<Consignment> consignments = new ArrayList<>();
		Map<String, Double> orderEntries = order.getOrderEntries();
		for (Warehouse warehouse : warehouses) {
			Map<String, Double> inventory = warehouse.getInventory();
			
			for (Map.Entry<String, Double> orderEntry : orderEntries.entrySet()) {
				Double amountFulfilled = 0.0;
				String sku = orderEntry.getKey();
				Double orderedQty = orderEntry.getValue();
				
				if (orderedQty == 0.0) {
					continue;
				}
				
				if (inventory.get(sku) != null) {
				
					Double qtyInInventory = inventory.get(sku);
					
					//Reduce the quantity in order and calculate the amountFulfilled
					if (orderedQty <= qtyInInventory) {
						orderEntry.setValue(0.0);
						amountFulfilled = orderedQty;
					} else {
						orderEntry.setValue(orderedQty - qtyInInventory);
						amountFulfilled = qtyInInventory;
					}
					
					//Reduce the inventory quantity
					qtyInInventory = qtyInInventory - orderedQty;
					inventory.put(sku, qtyInInventory);
						
					//Populate the output data structure
					Consignment consignment = new Consignment(warehouse.getLocation(), order.getDestination(), sku, amountFulfilled);
					consignments.add(consignment);
				}
				
			}
			
		}
		return consignments;
	}

	private void calculateFitness(Order order, List<Warehouse> warehouses) {
		for (Warehouse warehouse : warehouses) {
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
		
		for (Map.Entry<String, Double> entryForOrderEntries : orderEntries.entrySet()) {
			double quantityInOrder = entryForOrderEntries.getValue();
			double quantityInWarehouse = inventory.get(entryForOrderEntries.getKey()) == null ? 0 : inventory.get(entryForOrderEntries.getKey());
			
			quantityOfAllItemsInOrder = quantityOfAllItemsInOrder + quantityInOrder;
			
			if (quantityInWarehouse < quantityInOrder) {
				quantityNotFulfilled = quantityNotFulfilled + (quantityInOrder - quantityInWarehouse);
			}			
		}
		
		if (quantityNotFulfilled > 0) {
			percentFulfilled = (quantityOfAllItemsInOrder - quantityNotFulfilled)/quantityOfAllItemsInOrder;
		} else {
			percentFulfilled = 1;
		}
		return percentFulfilled;
	}

}
