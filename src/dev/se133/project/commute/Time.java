package dev.se133.project.commute;

/**
 * An immutable fixed point in time.
 */
public class Time implements Comparable<Time> {
	/**	The minimum legal value. */
	public static final int MIN_VALUE = 0;
	/**	The maximum legal value. */
	public static final int MAX_VALUE = 1439;
	/** The conversion rate between minutes and hours */
	public static final int MINUTES_TO_HOUR = 60;

	private Day day;
	private int totalMinutes;	// Minutes after start of day
	
	/**
	 * Returns a new {@code Time} object at a number of minutes after a source {@code Time} object.
	 * @param start	source time to increment from
	 * @param minutesAfter minutes to increment source time
	 * @return new time occurring a specified number of minutes after a source time
	 * @throws TimeOutOfBoundsException
	 */
	public static Time timeAfter(Time start, int minutesAfter) throws TimeOutOfBoundsException {	// TODO Should wrap to next day if overflow, no exception
		Time toReturn = new Time(start);
		toReturn.setTotalMinutes(toReturn.totalMinutes + minutesAfter);
		
		return toReturn;
	}
	
	/**
	 * Constructs a new time at the specified point.
	 * @param day day of time
	 * @param hour hour of the day
	 * @param minute minute of the day
	 * @throws TimeOutOfBoundsException if specified time is out of bounds
	 */
	public Time(Day day, int hour, int minute) throws TimeOutOfBoundsException {
		this(day, hour * MINUTES_TO_HOUR + minute);
	}
	/**
	 * Constructs a new time at a specified point.
	 * @param day day of time
	 * @param totalMinutes total minutes after the start of the day
	 * @throws TimeOutOfBoundsException if specified time is out of bounds
	 */
	public Time(Day day, int totalMinutes) throws TimeOutOfBoundsException {
		this.day = day;
		setTotalMinutes(totalMinutes);
	}
	/**
	 * Constructs a new time which is a copy of another time.
	 * @param toCopy time to copy
	 */
	public Time(Time toCopy) {
		this.day = toCopy.day;
		this.totalMinutes = toCopy.totalMinutes;
	}
	
	private void setTotalMinutes(int totalMinutes) throws TimeOutOfBoundsException {
		if (totalMinutes < MIN_VALUE || totalMinutes > MAX_VALUE)
			throw new TimeOutOfBoundsException(totalMinutes, MIN_VALUE, MAX_VALUE);
		
		this.totalMinutes = totalMinutes;
	}
	
	/** @return day of the week of this time */
	public Day getDay() {
		return day;
	}
	
	/** @return hour from 0-23 */
	public int getHour() {
		return totalMinutes / MINUTES_TO_HOUR;
	}
	/** @return minute from 0-59 */
	public int getMinute() {
		return totalMinutes % MINUTES_TO_HOUR;
	}
	
	/** @return minutes after 00:00 of the set day */
	public int getTotalMinutes() {
		return totalMinutes;
	}
	
	@Override
	public int compareTo(Time o) {
		return new Integer(totalMinutes).compareTo(o.totalMinutes);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + totalMinutes;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Time))
			return false;
		Time other = (Time) obj;
		if (totalMinutes != other.totalMinutes)
			return false;
		return true;
	}
}
