package dev.se133.project.member.garage;

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
	private Vehicle defaultVehicle;
	
	/**
	 * Constructs an empty garage.
	 */
	public Garage() {
		defaultVehicle = null;
	}
	
	/**
	 * Returns the vehicle in this garage with the greatest capacity.
	 * If the garage is empty, returns {@code null}.
	 * @return vehicle with greatest capacity, or {@code null}
	 */
	public Vehicle getLargestVehicle() {
		Vehicle largestVehicle = null;
		
		for (Vehicle vehicle : vehicles.values()) {
			if (largestVehicle == null || vehicle.getCapacity() > largestVehicle.getCapacity())
				largestVehicle = vehicle;
		}
		return largestVehicle;
	}
	
	/**
	 * Returns a copy of all the vehicles stored in this garage.
	 * @return all vehicles
	 */
	public Set<Vehicle> getVehicles() {
		return new HashSet<Vehicle>(vehicles.values());
	}
	/**
	 * @return default vehicle;
	 */
	public Vehicle getDefaultVehicle(){
		return defaultVehicle;
	}
	/**
	 * Adds a vehicle to this garage.
	 * @param name name to identify vehicle as
	 * @param vehicle vehicle to add
	 */
	public void addVehicle(String name, Vehicle vehicle) {
		vehicles.put(name, vehicle);
		defaultVehicle = vehicle;
	}
	/**
	 * Removes a vehicle with the specified name.
	 * @param name name of vehicle to remove
	 * @return removed vehicle, or {@code null} if no such vehicle
	 */
	public Vehicle removeVehicle(String name) {
		return vehicles.remove(name);
	}
	
	public String toString() {
		String vehics = "";
		for(Vehicle vehicle : vehicles.values()) {
			vehics += vehicle.toString() + "\n";
		}
		return vehics;
	}
	
}
