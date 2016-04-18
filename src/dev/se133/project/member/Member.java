package dev.se133.project.member;

import java.util.LinkedList;
import java.util.List;

import dev.se133.project.car.Car;
import dev.se133.project.car.CarListener;
import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.Address;
import dev.se133.project.member.garage.Garage;
import dev.se133.project.member.preferences.CommuteSchedule;
import dev.se133.project.member.wallet.Wallet;

/**
 * A basis member implementation
 */
public class Member implements Comparable<Member>, CarListener {
	private static final boolean SILENCE = false;	// TODO Debugging boolean
	
	private int id;
	private String name;
	private boolean driverStatus;
	private Address homeAddress;
	private Wallet wallet;
	private Garage registeredVehicles;
	private CommuteSchedule commuteTimes;
	private Carpool currentCarpool = null;	// Should not created with a predefined Carpool
	private MemberState currentState = new MemberState.Idle();	// Member should not be created during the middle of a trip
	private List<MemberListener> listeners = new LinkedList<>();
	
	/**
	 * Constructs a new member with custom attributes.
	 * @param id identification number, should be unique among all coexisting members
	 * @param name name identification
	 * @param driverStatus if {@code true}, this member may be selected to drive carpools
	 * @param homeAddress permanent home address
	 * @param wallet collection of loot owned by this member
	 * @param registeredVehicles set of vehicles registered to this member
	 * @param commuteTimes preferences to use when scheduling this member for carpools
	 */
	public Member(int id, String name, boolean driverStatus, Address homeAddress, Wallet wallet, Garage registeredVehicles, CommuteSchedule commuteTimes) {
		setId(id);
		setName(name);
		setDriverStatus(driverStatus);
		setHomeAddress(homeAddress);
		setWallet(wallet);
		setRegisteredVehicles(registeredVehicles);
		setCommuteTimes(commuteTimes);
	}
	
	/**
	 * Returns the car most appropriate to this member, given this member's current state.
	 * @return appropriate car
	 */
	public Car getCar() {
		return currentState.getCar(this);
	}
	
	/** @return this member's identification number */
	public int getId() {
		return id;
	}
	private void setId(int newId) {
		this.id = newId;
	}
	
	/** @return	this member's name */
	public String getName() {
		return name;
	}
	/** @param newName new name to set */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/** @return {@code true} if this member can drive */
	public boolean isDriver() {
		return driverStatus;
	}
	/** @param newDriverStatus new driver status to set */
	public void setDriverStatus(boolean newDriverStatus) {
		this.driverStatus = newDriverStatus;
	}
	
	/** @return address of this member's home */
	public Address getAddress() {
		return homeAddress;
	}
	/** @param newHomeAddress new home address to set */
	public void setHomeAddress(Address newHomeAddress) {
		this.homeAddress = newHomeAddress;
	}
	
	/** @return wallet owned by this member */
	public Wallet getWallet() {
		return wallet;
	}
	/** @param newWallet new wallet to set */
	public void setWallet(Wallet newWallet) {
		this.wallet = newWallet;
	}
	
	/** @return set of vehicles registered to this member */
	public Garage getRegisteredVehicles() {
		return registeredVehicles;
	}
	/** @param newRegisteredVehicles new set of registered vehicles to set */
	public void setRegisteredVehicles(Garage newRegisteredVehicles) {
		this.registeredVehicles = newRegisteredVehicles;
	}
	
	/** @return schedule of this member's preferred commuting times */
	public CommuteSchedule getCommuteTimes() {
		return commuteTimes;
	}
	/** @param newCommuteTimes new schedule of preferred commuting times to set */
	public void setCommuteTimes(CommuteSchedule newCommuteTimes) {
		this.commuteTimes = newCommuteTimes;
	}
	
	public Carpool getCurrentCarpool() {
		return currentCarpool;
	}
	public void setCurrentCarpool(Carpool newCurrentCarpool) {
		this.currentCarpool = newCurrentCarpool;
	}
	
	void setState(MemberState newState) {
		this.currentState = newState;
		
		notifyStateChanged();	// Notify all listeners of state change
	}
	private void notifyStateChanged() {
		for (MemberListener listener : listeners)
			listener.stateChanged(currentState);	// TODO Don't pass state object
	}
	
	/**
	 * Adds a listener to this member.
	 * @param listener listener to add
	 */
	public void addListener(MemberListener listener) {
		listeners.add(listener);
	}
	/**
	 * Removes a listener from this member
	 * @param listener listener to remove
	 */
	public void removeListener(MemberListener listener) {
		listeners.remove(listener);
	}
	/**
	 * Clears all listeners from this member.
	 */
	public void clearListeners() {
		listeners.clear();
	}
	
	/**
	 * Compares this member's ID number to another member's ID number.
	 */
	@Override
	public int compareTo(Member o) {
		return Integer.compare(id, o.getId());
	}
	
	public void memberAdded(Member added) {
		if (SILENCE)
			return;
		
		acknowledge(added.getName() + " has been added to the car.");
	}
	
	public void memberRemoved(Member removed) {
		if (SILENCE)
			return;
		
		acknowledge(removed.getName() + " has been removed from the car.");
	}
	
	public void driverSet(Member driver) {
		if (SILENCE)
			return;
		
		acknowledge(driver.getName() + " has been set as driver of the car.");
	}
	
	
	public void filled(long id) {
		if (SILENCE)
			return;
		
		acknowledge("Car " + id + " has been filled");
	}
	
	public void freed(long id) {
		if (SILENCE)
			return;
		
		acknowledge("Car " + id + " has a free seat");
	}
	
	private void acknowledge(String message) {		
		System.out.println(getName() + ": " + message);
	}
}
