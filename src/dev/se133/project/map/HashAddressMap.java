package dev.se133.project.map;

import java.util.HashMap;
import java.util.Set;

import dev.se133.project.commute.Address;

public class HashAddressMap implements AddressMap {

private  HashMap<Address, Double > commuteMap = new HashMap<>();
	
	public HashAddressMap(Address departure) {
		commuteMap.put(departure, 0.0);
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
	@Override
	public double getDistance(Address address1, Address address2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Address> getAllAddresses() {
		// TODO Auto-generated method stub
		return null;
	}

}
