package dev.se133.project.commute;

import java.util.HashMap;

public abstract class AddressMap {	// TODO What is this? Not used anywhere

	private  HashMap<Address, Double > commuteMap = new HashMap<>();
	
	public AddressMap(Address departure) {
		commuteMap.put(departure, 0.0);
	}
	/** @return distance to address */
	private double getCommuteDistance(Address address) {
		return commuteMap.get(address);
	}
	public HashMap<Address, Double> getCommuteMap() {
		return commuteMap;
	}
	/** Adds a new entry to the commuteMap hash. */
	private void addCommuteDistance(Address address, double distance) {
		commuteMap.put(address, distance);
	}
	
	private void removeCommuteDistance(Address address) {
		commuteMap.remove(address);
	}
}
