package dev.se133.project.car;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import dev.se133.project.carpool.CarpoolListener;
import dev.se133.project.member.Member;

/**
 * Representation of the inhabitants of a car.
 * Contains a finite number of inhabitants, exactly 1 of which is the designated driver.
 * The car capacity is set permanently during object construction.
 */
public class Car{
	
	/** Default maximum number of inhabitants */
	public static final int DEFAULT_CAPACITY = 5;
	
	private final int capacity;
	private Member driver;	// Reference to one of the inhabitants
	private Set<Member> inhabitants = new HashSet<>();
	private List<CarpoolListener> listeners = new LinkedList<>();
	
	/**
	 * Constructs a car of default capacity.
	 */
	public Car() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Constructs a car of a specified capacity.
	 * @param capacity maximum number of inhabitants
	 */
	public Car(int capacity) {
		this(capacity, null);
	}
	/**
	 * Constructs a car of a specified capacity and preset member.
	 * If the specified member is available to drive, that member is designated the car's driver.
	 * @param capacity maximum number of inhabitants
	 * @param member member preloaded into this car
	 */
	public Car(int capacity, Member member) {
		this.capacity = capacity;
		
		if (member != null) {
			addPassenger(member);
			selectDriver();
		}
	}
	
	/**
	 * Adds a new inhabitant to this car.
	 * @param inhabitant new member to add to car
	 * @return {@code true} if this car did not already contain the added member
	 * @throws FullCarException if adding this member would overflow this car's capacity
	 * @throws NoDriverException if adding this member would result in this car being unable to contain at least 1 driver
	 */
	public boolean addPassenger(Member inhabitant) {	// TODO addAsDriver boolean
		if (isFull())
			throw new FullCarException(capacity);
		if ((driver == null) && !(inhabitant.isDriver()) && (getAvailableSeats() <= 1))	// Cannot have a car full of only passengers
			throw new NoDriverException();
		
		return inhabitants.add(inhabitant);
	}
	/**
	 * Attempts to remove the specified inhabitant from this car.
	 * @param inhabitant member to remove from car
	 * @return {@code true} if specified inhabitant removed, {@code false} if no such inhabitant
	 */
	public boolean removePassenger(Member inhabitant) {
		return inhabitants.remove(inhabitant);
	}
	
	/**
	 * Adds a new member to this car as the designated driver.
	 * @param newDriver member to add as driver
	 * @return new driver
	 * @throws IllegalArgumentException if the specified member is not a driver
	 */
	public Member addDriver(Member newDriver) {
		if (!newDriver.isDriver())
			throw new IllegalArgumentException();
		
		this.driver = newDriver;
		inhabitants.add(newDriver);
		
		return this.driver;
	}
	
	/**
	 * Searches this car for an inhabitant with a state of {@code MemberState.Driver}.
	 * If such an inhabitant is located, that inhabitant is set as the driver.
	 * @return {@code true} if driver set successfully
	 */
	public Member selectDriver() {		
		for (Member inhabitant : inhabitants) {
			if (inhabitant.isDriver())
				driver = inhabitant;
				break;	// Found, set suitable candidate
		}
		
		return getDriver();
	}

	/** @return {@code true} if the number of inhabitants in the car matches its capacity */
	public boolean isFull() {
		return inhabitants.size() == capacity;
	}
	
	/** @return {@code true} if this car has a designated driver */
	public boolean hasDriver() {
		return driver != null;
	}
	
	/** @return number of inhabitants currently in this car */
	public int getSize() {
		return inhabitants.size();
	}
	/** @return maximum number of inhabitants this car may support */
	public int getCapacity() {
		return capacity;
	}
	/** @return number of remaining open seats in this car */
	public int getAvailableSeats() {
		return getCapacity() - getSize();
	}
	
	/** @return current driver, or {@code null} if there is none */
	public Member getDriver() {
		return driver;
	}
	/**
	 * Returns a set of all current passengers in this car.
	 * @return all current passengers
	 */
	public Set<Member> getPassengers() {
		Set<Member> passengers = new HashSet<>();
		
		for (Member inhabitant : inhabitants) {
			if (!inhabitant.equals(driver))
				passengers.add(inhabitant);
		}
		return passengers;
	}
	/**
	 * Returns all current inhabitants of this car.
	 * The returned objects are direct, mutable references to this car's inhabitants.
	 * @return all current inhabitants
	 */
	public Set<Member> getInhabitants() {
		return new HashSet<>(inhabitants);
	}
	
	/**
	 * Adds a listener to this car.
	 * @param listener listener to add
	 */
	public void addListener(CarpoolListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Returns the hashcode of this car.
	 * The hashcode is formulated using this car's capacity, driver's hashcode, and inhabitants' hashcodes.
	 * @return hashcode for this car
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + ((inhabitants == null) ? 0 : inhabitants.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Car))
			return false;
		
		Car other = (Car) obj;
		if (capacity != other.capacity)
			return false;
		if (driver == null) {
			if (other.driver != null)
				return false;
		} else if (!driver.equals(other.driver))
			return false;
		if (inhabitants == null) {
			if (other.inhabitants != null)
				return false;
		} else if (!inhabitants.equals(other.inhabitants))
			return false;
		
		return true;
	}
}
