package dev.se133.project.map;

import java.util.HashMap;
import java.util.Set;

import dev.se133.project.commute.Address;

public class HashAddressMap implements AddressMap {

	private  HashMap<Address, double[] > commuteMap;
	private double[] coord;
	
	public HashAddressMap() {
		commuteMap = new HashMap<>();
	}
	
	public HashMap<Address, double[]> getCommuteMap() {
		return commuteMap;
	}
	/** Adds a new entry to the commuteMap hash. */
	private void addAddress(Address address, double x, double y) {
		coord = new double[2];
		coord[0] = x;
		coord[1] = y;
		commuteMap.put(address, coord);
	}
	
	private void removeAddress(Address address) {
		commuteMap.remove(address);
	}
	@Override
	public double getDistance(Address address1, Address address2) {
		double xDist, yDist;
		
		xDist = Math.abs(commuteMap.get(address1)[0] - commuteMap.get(address2)[0]);
		yDist = Math.abs(commuteMap.get(address1)[1] - commuteMap.get(address2)[1]);
		
		// TODO Auto-generated method stub
		return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
	}

	@Override
	public Set<Address> getAllAddresses() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
