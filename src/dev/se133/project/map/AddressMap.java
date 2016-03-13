package dev.se133.project.map;

import java.util.Set;

import dev.se133.project.commute.Address;

/**
 * Specifies functions of a map of {@code Address} objects.
 */
public interface AddressMap {
	/**
	 * Returns the distance (in some abstract units) between 2 addresses contained in this map.
	 * If at least 1 of the specified addresses is not contained in this map, returns -1;
	 * @param address1 first address
	 * @param address2 second address
	 * @return distance between address, or {@code -1} if the backing map does not contain both addresses
	 */
	double getDistance(Address address1, Address address2);
	
	/**
	 * Returns the time (in some abstract units) between 2 addresses contained in this map.
	 * If at least 1 of the specified addresses is not contained in this map, returns -1;
	 * @param address1 first address
	 * @param address2 second address
	 * @return time between address, or {@code -1} if the backing map does not contain both addresses
	 */
	double getTime(Address address1, Address address2);
	
	/**
	 * Returns a collection of all the addresses contained in this map.
	 * @return all addresses contained in this map
	 */
	Set<Address> getAllAddresses();
}
