package dev.se133.carpool.member;

import java.util.HashMap;
import java.util.Map;

import dev.se133.carpool.commute.Address;
import dev.se133.carpool.commute.schedule.CommuteSchedule;
import dev.se133.carpool.commute.schedule.ConcreteCommuteSchedule;
import dev.se133.carpool.member.property.vehicle.Vehicle;
import dev.se133.carpool.member.state.MemberState;

/**
 * A concrete member implementation
 */
public class ConcreteMember implements Member {
	private String name;
	private Address address;
	private Map<String, Vehicle> vehicles = new HashMap<>();
	private MemberState state;
	private CommuteSchedule preferredCommutes = new ConcreteCommuteSchedule();
	
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
	
	@Override
	public void addVehicle(String name, Vehicle vehicle) {
		vehicles.put(name, vehicle);
	}
	@Override
	public Vehicle removeVehicle(String name) {
		return vehicles.remove(name);
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Address getAddress() {
		return address;
	}
	@Override
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public Vehicle[] getVehicles() {
		return vehicles.values().toArray(new Vehicle[vehicles.size()]);
	}
	
	@Override
	public MemberState getState() {
		return state;
	}
	@Override
	public void setState(MemberState state) {
		this.state = state;
	}
}
