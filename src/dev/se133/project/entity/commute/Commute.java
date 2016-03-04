package dev.se133.project.entity.commute;

import java.util.Set;
import java.util.TreeSet;

import dev.se133.project.entity.Day;

/**
 * A one-way commute.
 * Consists of a day of the week, departure, arrival, and stop points and times.
 */
public class Commute implements Comparable<Commute> {
	private Day day;	// Day of commute
	private CommutePoint departure, arrival;
	private Set<CommutePoint> stops = new TreeSet<>();
	
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
	
	/** @return all stops, sorted by time ascending */
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
	 * The comparison is based on a lexicographical ordering on day, departure time, arrival time, number of stops, set of stops hash.
	 */
	@Override
	public int compareTo(Commute o) {
		int dayCompare = day.compareTo(o.day);
		if (dayCompare != 0)
			return dayCompare;
		
		int departureCompare = departure.compareTo(o.departure);
		if (departureCompare != 0)
			return departureCompare;
		
		int arrivalCompare = arrival.compareTo(o.arrival);
		if (arrivalCompare != 0)
			return arrivalCompare;
		
		int stopSizeCompare = Integer.compare(stops.size(), o.stops.size());
		if (stopSizeCompare != 0)
			return stopSizeCompare;
		
		return Integer.compare(stops.hashCode(), o.stops.hashCode());
	}
}
