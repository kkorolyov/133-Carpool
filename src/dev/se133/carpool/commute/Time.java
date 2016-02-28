package dev.se133.carpool.commute;

import dev.se133.carpool.commute.exception.TimeOutOfBoundsException;

/**
 * An immutable fixed point in time within a day.
 */
public class Time implements Comparable<Time> {
	private static final int hourMin = 0, hourMax = 23, minuteMin = 0, minuteMax = 59;
	private static final int minutesPerHour = 60;
	
	private int totalMinutes;	// Minutes after 00:00
	
	/**
	 * Constructs a new time at the specified point.
	 * @param hour hour from 0-23
	 * @param minute minute from 0-59
	 * @throws TimeOutOfBoundsException if specified hour or minute are out of bounds
	 */
	public Time(int hour, int minute) throws TimeOutOfBoundsException {
		setHour(hour);
		setMinute(minute);
	}
	private void setHour(int hour) throws TimeOutOfBoundsException {
		if (hour < hourMin || hour > hourMax)
			throw new TimeOutOfBoundsException(hour, hourMin, hourMax);
		
		totalMinutes += (hour * minutesPerHour);
	}
	private void setMinute(int minute) throws TimeOutOfBoundsException {
		if (minute < minuteMin || minute > minuteMax)
			throw new TimeOutOfBoundsException(minute, minuteMin, minuteMax);
		
		totalMinutes += minute;
	}
	
	/** @return hour from 0-23 */
	public int getHour() {
		return totalMinutes / minutesPerHour;
	}
	/** @return minute from 0-59 */
	public int getMinute() {
		return totalMinutes % minutesPerHour;
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
