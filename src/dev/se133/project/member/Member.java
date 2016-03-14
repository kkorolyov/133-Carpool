package dev.se133.project.member;

import dev.se133.project.commute.Address;
import dev.se133.project.schedule.CommuteSchedule;

/**
 * General member interface.
 */
public interface Member extends Comparable<Member> {
	
	/** @return unique member ID */
	int getId();
	
	/** @return member's name */
	String getName();
	/** @param name new name */
	void setName(String name);
	
	/** @return member address */
	Address getAddress();
	/** @param address new address */
	void setAddress(Address address);
	
	/** @return collection of vehicles owned by this member */
	Garage getGarage();
	/** @param garage new garage */
	void setGarage(Garage garage);
	
	/** @return schedule of preferred commutes */
	CommuteSchedule getPreferredCommutes();
	/** @param preferredCommutes new schedule of preferred commutes */
	void setPreferredCommutes(CommuteSchedule preferredCommutes);
	
	/** @return current state */
	MemberState getState();
	/** @param state new state */
	void setState(MemberState state);
	
	/**
	 * Adds a listener to this member.
	 * @param listener listener to add
	 */
	void addListener(MemberListener listener);
	/**
	 * Removes a listener from this member.
	 * @param listener listener to remove
	 */
	void removeListener(MemberListener listener);
	/**
	 * Removes all listeners from this member.
	 */
	void clearListeners();
	
	/**
	 * @return distance from school
	 */
	double getDistanceFromDestination();
	
	double getMaxTime();
	
	double getMaxDistance();
}
