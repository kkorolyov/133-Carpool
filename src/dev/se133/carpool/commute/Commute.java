package dev.se133.carpool.commute;

import java.util.HashSet;
import java.util.Set;

/**
 * A one-way commute.
 * Consists of a day of the week, departure, arrival, and stop points and times.
 */
public class Commute implements Comparable<Commute> {
	private Day day;	// Day of commute
	private CommutePoint departure, arrival;
	private Set<CommutePoint> stops = new HashSet<>();
	
	/**
	 * Constructs a new commute with no additional stops.
	 * @see #Commute(Day, CommutePoint, CommutePoint, CommutePoint[])
	 */
	public Commute(Day day, CommutePoint departure, CommutePoint arrival) {
		this(day, departure, arrival, null);
	}
	/**
	 * Constructs a new commute with additional stops.
	 * @param day day of the commute
	 * @param departure departure address and time
	 * @param arrival arrival address and time
	 * @param stops stop addresses and times
	 * 
	 */
	public Commute(Day day, CommutePoint departure, CommutePoint arrival, CommutePoint[] stops) {
		setDay(day);
		setDeparture(departure);
		setArrival(arrival);
		
		if (stops != null) {
			for (CommutePoint stop : stops)
				addStop(stop);
		}
	}
	
	/** @return day of commute */
	public Day getDay() {
		return day;
	}
	/** @param day new day of commute */
	public void setDay(Day day) {
		this.day = day;
	}
	
	/** @return departure point and time */
	public CommutePoint getDeparture() {
		return departure;
	}
	/** @param departure new departure point and time */
	public void setDeparture(CommutePoint departure) {
		this.departure = departure;	// TODO Validate
	}
	
	/** @return arrival point and time */
	public CommutePoint getArrival() {
		return arrival;
	}
	/** @param arrival new arrival point and time */
	public void setArrival(CommutePoint arrival) {
		this.arrival = arrival;	// TODO Validate
	}
	
	/** @return all stops */
	public CommutePoint[] getStops() {
		return stops.toArray(new CommutePoint[stops.size()]);
	}
	/**
	 * Adds a stop.
	 * @param stop stop to add
	 */
	public void addStop(CommutePoint stop) {
		stops.add(stop);	// TODO Validate
	}
	/**
	 * Removes a stop.
	 * @param stop stop to remove
	 * @return {@code true} if stop removed, {@code false} if no such stop
	 */
	public boolean removeStop(CommutePoint stop) {
		return stops.remove(stop);
	}
	
	/**
	 * Compares this commute to a specified commute.
	 * <p>First, compares the days of both commutes.
	 * If the days are the same, compares the departure times.
	 * @return a negative integer, zero, or positive integer if this commute's departure occurs earlier, at the same time, or after the compared commute's departure, respectively
	 */
	@Override
	public int compareTo(Commute o) {
		if (day.compareTo(o.day) != 0)
			return day.compareTo(o.day);
		
		return departure.compareTo(o.departure);
	}
}
