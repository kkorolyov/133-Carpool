package dev.se133.project.entity;

import java.util.HashSet;
import java.util.Set;

import dev.se133.project.entity.commute.Commute;
import dev.se133.project.entity.member.Member;

/**
 * A one-way trip consisting of a commute and a driver and passengers.
 */
public class Carpool implements Comparable<Carpool> {	// TODO Refactor into a Car and Commute
	private static final int maxPassengers = 4;

	// TODO Identifier
	private Commute commute;
	private Car car;
	
	private Member driver;
	private Set<Member> passengers = new HashSet<>();
	
	/**
	 * Constructs a new round-trip carpool.
	 */
	public Carpool(Commute commute, Member driver, Car car) {
		setCommute(commute);
		setDriver(driver);
		setCar(car);
		
		if (passengers != null) {
			for (Member passenger : passengers)
				addPassenger(passenger);
		}
	}
	
	/** @return commute of stop location and times */
	public Commute getCommute() {
		return commute;
	}
	/**
	 * Sets the commute.
	 * @param commute new commute
	 */
	public void setCommute(Commute commute) {
		this.commute = commute;
	}
	
	/** @return commute's driver, or {@code null} if there is no driver */
	public Member getDriver() {
		return driver;
	}
	/** @param driver new driver */
	public void setDriver(Member driver) {
		this.driver = driver;
	}
	
	/** @param car new car*/
	public void setCar(Car car) {
		this.car = car;
	}
	
	/** @return all passengers */
	public Member[] getPassengers() {
		return passengers.toArray(new Member[passengers.size()]);
	}
	/** 
	 * Adds a passenger if there is room.
	 * @param passenger passenger to add
	 * @return {@code true} if passenger added successfully, {@code false} if adding would overfill the carpool
	 */
	public boolean addPassenger(Member passenger) {
		if (isFull())
			return false;
		
		passengers.add(passenger);	// TODO Validate, unique
		return true;
	}
	/**
	 * Removes a passenger.
	 * @param passenger passenger to remove
	 * @return {@code true} if passenger removed, {@code false} if no such stop
	 */
	public boolean removePassenger(Member passenger) {
		return passengers.remove(passenger);
	}
	
	/** @return {@code true} if the carpool has a driver and max passengers */
	public boolean isFull() {
		return ((driver != null) && (passengers.size() == maxPassengers));
	}

	/**
	 * Compares this carpool to a specified carpool.
	 * The comparison is based on a lexicographical ordering on commute, driver.
	 */
	@Override
	public int compareTo(Carpool o) {
		int commuteCompare = commute.compareTo(o.commute);
		if (commuteCompare != 0)
			return commuteCompare;
		
		return driver.compareTo(o.driver);
	}
}
