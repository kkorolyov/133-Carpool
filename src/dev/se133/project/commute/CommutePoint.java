package dev.se133.project.commute;

/**
 * A point in a commute consisting of an address and a time.
 * @see Address
 * @see Time
 */
public class CommutePoint implements Comparable<CommutePoint> {
	private Address address;
	private Time time;
	
	/**
	 * Constructs a new point at the specified address and time.
	 * @param address address of occurrence
	 * @param time time of occurrence
	 */
	public CommutePoint(Address address, Time time) {
		setAddress(address);
		setTime(time);
	}
	/**
	 * Constructs a new point which is a copy of another point.
	 * @param toCopy point to copy
	 */
	public CommutePoint(CommutePoint toCopy) {
		this(new Address(toCopy.address), new Time(toCopy.time));
	}
	private void setAddress(Address address) {
		this.address = address;
	}
	private void setTime(Time time) {
		this.time = time;
	}
	
	/** @return address where this point occurs */
	public Address getAddress() {
		return address;
	}
	/** @return time when this point occurs */
	public Time getTime() {
		return time;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CommutePoint))
			return false;
		CommutePoint other = (CommutePoint) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	
	/**
	 * Compares the occurrence times of this point and another point.
	 * @param o point to compare to
	 * @return -1, 0, or 1 if this point occurs before, during, or after the compared point
	 */
	@Override
	public int compareTo(CommutePoint o) {
		return time.compareTo(o.getTime());
	}
}
