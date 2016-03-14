package dev.se133.project.member;

import java.util.HashMap;
import java.util.Map;

import javax.swing.event.ChangeEvent;

import dev.se133.project.commute.Address;
import dev.se133.project.observer.*;
import dev.se133.project.schedule.CommuteSchedule;

/**
 * A basis member implementation
 */
public class BasicMember implements Member {
	private int id;
	private String name;
	private Address address;
	private Garage garage = new Garage();
	private CommuteSchedule preferredCommutes;
	private State state;

	private Observer[] observers = new MemberObserver[10];
	// TODO Ref to set of carpools?
	private int distanceFromDestination = 10;
	
	
	//Driver attributes
	private int maxTime;	//The maximum amount of time for which the driver is willing to commute
	private int maxDistance;//The maximum amount of distance the driver is willing to drive

	/** 
	 * Constructs a new member.
	 * <p> The member has an empty schedule of preferred commutes.
	 * <br>The member has an initial state of {@code MemberState.Passenger}
	 * @see #BasicMember(int, String, Address, CommuteSchedule, State)
	 */
	public BasicMember(int id, String name, Address address) {
		this(id, name, address, null);
	}
	/** 
	 * Constructs a new member.
	 * <p>The member has an initial state of {@code MemberState.Passenger}
	 * @see #BasicMember(int, String, Address, CommuteSchedule, State)
	 */
	public BasicMember(int id, String name, Address address, CommuteSchedule preferredCommutes) {
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
	public BasicMember(int id, String name, Address address, CommuteSchedule preferredCommutes, State state) {
		this.id = id;
		setName(name);
		setAddress(address);
		setPreferredCommutes(preferredCommutes);
		setState(state);
	}
	
	private void notifyObserver(int obv){
		observers[obv].stateChanged(new ChangeEvent(this));
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
	
	@Override
	public Garage getGarage() {
		return garage;
	}
	@Override
	public void setGarage(Garage garage) {
		this.garage = garage;
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
	
	@Override
	public double getDistanceFromDestination() {
		return distanceFromDestination;
	}
	
	@Override
	public double getMaxTime() {
		return maxTime;
	}
	
	@Override
	public double getMaxDistance() {
		return maxDistance;
	}
}
