package org.arunava.entities;

import java.util.Map;

public class Warehouse {
	
	String location;
	
	Map <String, Double> inventory;
	
	Double percentFulfilled;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Map<String, Double> getInventory() {
		return inventory;
	}

	public void setInventory(Map<String, Double> inventory) {
		this.inventory = inventory;
	}
	
	public Double getPercentFulfilled() {
		return percentFulfilled;
	}
	
	public void setPercentFulfilled(Double percentFulfilled) {
		this.percentFulfilled = percentFulfilled;
	}
	
}
