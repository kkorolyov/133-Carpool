package dev.se133.project.member;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A collection of {@code Vehicle} objects.
 * @see Vehicle
 */
public class Garage {
	private final Map<String, Vehicle> vehicles = new HashMap<>();
	
	/**
	 * Constructs an empty garage.
	 */
	public Garage() {
		// No-arg
	}
	
	/**
	 * Returns a copy of all the vehicles stored in this garage.
	 * @return all vehicles
	 */
	public Set<Vehicle> getVehicles() {
		return new HashSet<Vehicle>(vehicles.values());
	}
	/**
	 * Adds a vehicle to this garage.
	 * @param name name to identify vehicle as
	 * @param vehicle vehicle to add
	 */
	public void addVehicle(String name, Vehicle vehicle) {
		vehicles.put(name, vehicle);
	}
	/**
	 * Removes a vehicle with the specified name.
	 * @param name name of vehicle to remove
	 * @return removed vehicle, or {@code null} if no such vehicle
	 */
	public Vehicle removeVehicle(String name) {
		return vehicles.remove(name);
	}
}
