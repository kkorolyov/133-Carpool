package dev.se133.carpool.member;

import java.util.HashMap;
import java.util.Map;

import dev.se133.carpool.commute.Address;
import dev.se133.carpool.commute.schedule.CommuteSchedule;
import dev.se133.carpool.commute.schedule.ConcreteCommuteSchedule;
import dev.se133.carpool.member.property.vehicle.Vehicle;
import dev.se133.carpool.member.state.MemberState;
import dev.se133.carpool.member.state.State;

/**
 * A concrete member implementation
 */
public class ConcreteMember implements Member {
	private int id;
	private String name;
	private Address address;
	private Map<String, Vehicle> vehicles = new HashMap<>();
	private CommuteSchedule preferredCommutes = new ConcreteCommuteSchedule();
	// TODO Ref to set of carpools?
	private State state;

	/** 
	 * Constructs a new member.
	 * <p> The member has an empty schedule of preferred commutes.
	 * <br>The member has an initial state of {@code MemberState.Passenger}
	 * @see #ConcreteMember(int, String, Address, CommuteSchedule, State)
	 */
	public ConcreteMember(int id, String name, Address address) {
		this(id, name, address, null);
	}
	/** 
	 * Constructs a new member.
	 * <p>The member has an initial state of {@code MemberState.Passenger}
	 * @see #ConcreteMember(int, String, Address, CommuteSchedule, State)
	 */
	public ConcreteMember(int id, String name, Address address, CommuteSchedule preferredCommutes) {
		this(id, name, address, preferredCommutes, new MemberState.Passenger());
	}
	/**
	 * Constructs a new member.
	 * @param id member's unique ID
	 * @param name member's name
	 * @param address member's address
	 * @param preferredCommutes schedule of preferred commutes
	 * @param state member's initial state
	 */
	public ConcreteMember(int id, String name, Address address, CommuteSchedule preferredCommutes, State state) {
		this.id = id;
		setName(name);
		setAddress(address);
		setPreferredCommutes(preferredCommutes);
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
	public int getId() {
		return id;
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
	
	// TODO getVehicleNames
	@Override
	public Vehicle[] getVehicles() {
		return vehicles.values().toArray(new Vehicle[vehicles.size()]);	// TODO Exception if passenger state?
	}
	
	@Override
	public CommuteSchedule getPreferredCommutes() {
		return preferredCommutes;
	}
	@Override
	public void setPreferredCommutes(CommuteSchedule preferredCommutes) {
		this.preferredCommutes = preferredCommutes;
	}
	
	@Override
	public State getState() {
		return state;
	}
	@Override
	public void setState(State state) {
		this.state = state;
	}
	
	@Override
	public int compareTo(Member o) {
		return Integer.compare(id, o.getId());
	}
}
