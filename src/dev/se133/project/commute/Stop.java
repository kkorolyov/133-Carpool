package dev.se133.project.commute;

/**
 * A fixed, unique point in time and space.
 * Describes a single stop of a commute.
 */
public class Stop implements Comparable<Stop> {
	private static final char TO_STRING_DELIMETER = ',';
	
	private Time time;
	private Address address;
	
	/**
	 * Constructs a stop at a time and address
	 * @param time time of stop
	 * @param address address of stop
	 */
	public Stop(Time time, Address address) {
		this.time = time;
		this.address = address;
	}
	
	/** @return time of this stop */
	public Time getTime() {
		return time;
	}
	/** @return address of this stop */
	public Address getAddress() {
		return address;
	}
	
	/**
	 * Compares based on time.
	 */
	@Override
	public int compareTo(Stop o) {
		return time.compareTo(o.time);
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
		if (!(obj instanceof Stop))
			return false;
		Stop other = (Stop) obj;
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
	
	/** @return time and place as a concatenated string */
	@Override
	public String toString() {
		String toString = time.toString() + TO_STRING_DELIMETER + address.toString();
		
		return toString;
	}
}
