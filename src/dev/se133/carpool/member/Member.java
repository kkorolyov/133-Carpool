package dev.se133.carpool.member;

import dev.se133.carpool.commute.Address;
import dev.se133.carpool.member.property.vehicle.Vehicle;
import dev.se133.carpool.member.state.MemberState;

/**
 * General member interface.
 */
public interface Member {
	
	/**
	 * Adds a vehicle under this member.
	 * @param name name to identify vehicle as
	 * @param vehicle vehicle to add
	 */
	void addVehicle(String name, Vehicle vehicle);
	/**
	 * Removes a vehicle with the specified name.
	 * @param name name of vehicle to remove
	 * @return removed vehicle, or {@code null} if no such vehicle
	 */
	Vehicle removeVehicle(String name);
	
	/** @return member's name */
	String getName();
	/** @param name new name */
	void setName(String name);
	
	/** @return member address */
	Address getAddress();
	/** @param address new address */
	void setAddress(Address address);
	
	/** @return all member's vehicles */
	Vehicle[] getVehicles();
	
	/** @return current state */
	MemberState getState();
	
	/** @param state new state */
	void setState(MemberState state);
}
