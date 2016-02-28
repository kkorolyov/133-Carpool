package dev.se133.carpool.commute;

import java.util.LinkedList;
import java.util.List;

import dev.se133.carpool.member.Member;

/**
 * A one-way trip consisting of a commute and a driver and passengers.
 */
public class Carpool {
	private static final int maxPassengers = 4;

	private Commute commute;
	private Member driver;
	private List<Member> passengers = new LinkedList<>();
	
	/**
	 * Constructs a new round-trip carpool.
	 */
	public Carpool(Commute commute, Member driver, Member[] passengers) {
		setCommute(commute);
		setDriver(driver);
		
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
		
		passengers.add(passenger);	// TODO Validate
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
}
