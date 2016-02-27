package dev.se133.carpool.commute;

/**
 * An immutable fixed location and time of a commute.
 */
public class CommutePoint {
	private Address address;
	private Time time;
	
	/**
	 * Constructs a new point at the specified address and time.
	 * @param address commute point's address of occurrence
	 * @param time commute point's time of occurrence
	 */
	public CommutePoint(Address address, Time time) {
		setAddress(address);
		setTime(time);
	}
	private void setAddress(Address address) {
		this.address = address;
	}
	private void setTime(Time time) {
		this.time = time;
	}
	
	/** @return address */
	public Address getAddress() {
		return address;
	}
	/** @return time */
	public Time getTime() {
		return time;
	}
}
