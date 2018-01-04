package org.arunava.entities;

import java.util.Map;

public class Order {
	
	String destination;
	
	Map<String, Double> orderEntries;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Map<String, Double> getOrderEntries() {
		return orderEntries;
	}

	public void setOrderEntries(Map<String, Double> orderEntries) {
		this.orderEntries = orderEntries;
	}
	
}
