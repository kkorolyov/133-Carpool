package dev.se133.carpool.member;

import java.util.LinkedList;
import java.util.List;

import dev.se133.carpool.member.property.Address;
import dev.se133.carpool.member.property.vehicle.Vehicle;
import dev.se133.carpool.member.state.MemberState;

/**
 * A concrete member implementation
 */
public class ConcreteMember implements Member {
	private String name;
	private Address address;
	private List<Vehicle> vehicles = new LinkedList<>();
	
	private MemberState state;
	
	/** 
	 * Constructs a new member without an initial state.
	 * @see #ConcreteMember(String, Address, MemberState)
	 */
	public ConcreteMember(String name, Address address) {
		this(name, address, null);
	}
	/**
	 * Constructs a new member.
	 * @param name member's name
	 * @param address member's address
	 * @param state member's initial state
	 */
	public ConcreteMember(String name, Address address, MemberState state) {
		setName(name);
		setAddress(address);
		setState(state);
	}
	
	/**
	 * Adds a vehicle under this member.
	 * @param vehicle vehicle to add
	 */
	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}
	/**
	 * Remove a vehicle.
	 * @param vehicle vehicle to remove
	 * @return {@code true} if vehicle removed, {@code false} if no such vehicle
	 */
	public boolean removeVehicle(Vehicle vehicle) {
		return vehicles.remove(vehicle);
	}
	/**
	 * Removes a vehicle at the specified index.
	 * @param i index of vehicle to remove
	 * @return removed vehicle
	 */
	public Vehicle removeVehicle(int i) {
		return vehicles.remove(i);
	}
	
	/** @return member's name */
	public String getName() {
		return name;
	}
	/** @param name new name */
	public void setName(String name) {
		this.name = name;
	}
	
	/** @return member address */
	public Address getAddress() {
		return address;
	}
	/** @param address new address */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	/** @return all member's vehicles */
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	
	/** @return current state */
	public MemberState getState() {
		return state;
	}
	/** @param state new state */
	public void setState(MemberState state) {
		this.state = state;
	}
}
