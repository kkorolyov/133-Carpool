package dev.se133.project.commute;

import java.util.HashSet;
import java.util.Set;

import dev.se133.project.member.BasicMember;
import dev.se133.project.member.Member;
import dev.se133.project.member.MemberState;
import dev.se133.project.observer.MemberObserver;
import dev.se133.project.observer.Observer;

/**
 * Representation of the inhabitants of a car.
 * Contains a finite number of inhabitants, exactly 1 of which is the designated driver.
 * The car capacity is set permanently during object construction.
 */
public class Car {
	/** Default maximum number of inhabitants */
	public static final int DEFAULT_CAPACITY = 5;
	public static int totalCarpools = 0;
	public int carpoolID;
	
	private final int capacity;
	private Member driver;	// Reference to one of the inhabitants
	private Set<Member> inhabitants = new HashSet<>();
	
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
		this.capacity = capacity;
		totalCarpools++;
		this.carpoolID = totalCarpools;
	}
	
	/**
	 * Adds a new inhabitant to this car.
	 * @param inhabitant new member to add to car
	 * @return {@code true} if this car did not already contain the added member
	 * @throws FullCarException if adding this member would overflow this car's capacity
	 * @throws NoDriverException if adding this member would result in this car being unable to contain at least 1 driver
	 */
	public boolean addPassenger(Member inhabitant) throws FullCarException, NoDriverException {
		if (isFull())
			throw new FullCarException(capacity);
		if ((driver == null) && !(inhabitant.getState() instanceof MemberState.Driver) && (getAvailableSeats() <= 1))	// Cannot have a car full of only passengers
			throw new NoDriverException();
		
		
		changeMessage(" was added to ", inhabitant);
		if(inhabitants.size() == capacity-1)
			stateMessage(" is full.", inhabitant);
		return inhabitants.add(inhabitant);
	}
	/**
	 * Attempts to remove the specified inhabitant from this car.
	 * @param inhabitant member to remove from car
	 * @return {@code true} if specified inhabitant removed, {@code false} if no such inhabitant
	 */
	public boolean removePassenger(Member inhabitant) {
		if(inhabitants.remove(inhabitant))
		{
			changeMessage(" removed from ", inhabitant);
			if(inhabitants.size() == capacity-1)
				stateMessage(" is no longer full.", inhabitant);
			return true;
		}
		return false;
	}
	
	/**
	 * Sets an inhabitant from this car as the car's driver.
	 * The inhabitant must be both already in the car and have a state of {@code MemberState.Driver}.
	 * @param inhabitant member of this car to set as driver
	 * @return {@code true} if driver set successfully
	 */
	public boolean setDriver(Member inhabitant) {		
		for (Member currentInhabitant : inhabitants) {
			if (currentInhabitant.equals(inhabitant)) {
				if (currentInhabitant.getState() instanceof MemberState.Driver) {
					
					driver = currentInhabitant;
					changeMessage(" is the driver for ", currentInhabitant);
				}
				break;	// Valid or not, equal inhabitant located, ok to break
			}
		}
		return getDriver() != null;
	}
	/**
	 * Searches this car for an inhabitant with a state of {@code MemberState.Driver}.
	 * If such an inhabitant is located, that inhabitant is set as the driver.
	 * @return {@code true} if driver set successfully
	 */
	public boolean setDriver() {		
		for (Member inhabitant : inhabitants) {
			if (inhabitant.getState() instanceof MemberState.Driver) {
				driver = inhabitant;
				changeMessage(" is the driver for ", inhabitant);
				
				break;	// Found, set suitable candidate
			}
		}
		return getDriver() != null;
	}

	/** @return {@code true} if the number of inhabitants in the car matches its capacity */
	public boolean isFull() {
		return inhabitants.size() == capacity;
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
	/** @return carpool ID*/
	public int getID(){
		return carpoolID;
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

	private void changeMessage(String action, Member newMember)
	{
		newMember.notification("You were " + action + "carpool " + this.getID());
		for(Member inhabitant : inhabitants)
		inhabitant.notification(newMember.getName() + action + "carpool " + this.getID());
		
		System.out.println("\n");
	}
	private void stateMessage(String action, Member newMember)
	{
		newMember.notification("Carpool " + this.getID() + action);
		for(Member inhabitant : inhabitants)
			inhabitant.notification("Carpool " + this.getID() + action);
	
		System.out.println("\n");
	}
}
