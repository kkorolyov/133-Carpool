package dev.se133.project.car;

import dev.se133.project.member.Member;

/**
 * Listens to interesting {@code Car} events.
 * @see Car
 */
public interface CarListener {
	/**
	 * Called when a member has been added to a car.
	 * @param added most-recently-added member
	 */
	void memberAdded(Member added);
	/**
	 * Called when a member has been removed from a car.
	 * @param removed most-recently-removed member
	 */
	void memberRemoved(Member removed);
	/**
	 * Called when a member has been designated a driver of a car.
	 * @param driver newly-designated driver
	 */
	void driverSet(Member driver);
	
	/**
	 * Called when a car has been filled to capacity.
	 * @param id ID of the car notifying of this event
	 */
	void filled(long id);	// TODO ID not needed?
	/**
	 * Called when a car has recently obtained a free seat
	 * @param id ID of the car notifying of this event
	 */
	void freed(long id);
}
