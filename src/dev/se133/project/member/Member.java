package dev.se133.project.member;

import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.Address;
import dev.se133.project.member.car.Car;
import dev.se133.project.member.garage.Garage;
import dev.se133.project.member.wallet.Wallet;
import dev.se133.project.schedule.CommuteSchedule;

/**
 * General member interface.
 */
public interface Member extends Comparable<Member> {
	/**
	 * Returns a car object dependent on this member's state at the time of this method's invocation.
	 * <ul>
	 * <li> Passenger --> null
	 * <li> Driver --> a new car with capacity equal to this member's largest vehicle's capacity
	 * <li> Riding --> the car this member is riding in
	 * <li> Driving --> the car this member is driving
	 * </ul>
	 * @return appropriate car
	 */
	Car getCar();
	
	/**
	 * Returns the wallet owned by this member.
	 * @return this member's wallet
	 */
	Wallet getWallet();
	
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
	
	/** @return the carpool this member is currently in, or {@code null} if not attached to a carpool */
	Carpool getCurrentCarpool();
	/** @param carpool new carpool */
	void setCurrentCarpool(Carpool carpool);
	
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
