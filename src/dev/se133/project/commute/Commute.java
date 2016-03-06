package dev.se133.project.commute;

import java.util.Set;
import java.util.TreeSet;

/**
 * A one-way commute.
 * Consists of a day of the week, departure, arrival, and stop points and times.
 */
public class Commute {
	private Day day;	// Day of commute
	private TreeSet<CommutePoint> stops = new TreeSet<>();	// Ordered by time ascending
	
	/**
	 * Constructs a new commute with no stops.
	 * @param day day of commute
	 */
	public Commute(Day day) {
		this(day, null);
	}
	/**
	 * Constructs a new commute.
	 * @param day day of commute
	 * @param stops all stops in the commute
	 */
	public Commute(Day day, Set<CommutePoint> stops) {
		setDay(day);
		setStops(stops);
	}
	private void setStops(Set<CommutePoint> stops) {
		if (stops == null)
			return;
		
		for (CommutePoint stop : stops) {
			if (stop != null)
				addStop(new CommutePoint(stop));	// To protect against outside access
		}
	}
	
	/**
	 * Adds a stop.
	 * If a stop equivalent to the specified stop is already in this commute, this method does nothing.
	 * @param stop stop to add
	 */
	public void addStop(CommutePoint stop) {
		if (stop == null)
			throw new IllegalArgumentException();
		
		stops.add(stop);
	}
	/**
	 * Removes a stop.
	 * @param stop stop to remove
	 * @return {@code true} if stop removed, {@code false} if no such stop
	 */
	public boolean removeStop(CommutePoint stop) {
		return stops.remove(stop);
	}
	
	/** @return day of commute */
	public Day getDay() {
		return day;
	}
	/** @param day new day of commute */
	public void setDay(Day day) {
		this.day = day;
	}
	
	/** @return earliest stop in this commute */
	public CommutePoint getDeparture() {
		return stops.first();	// Departure is the earliest
	}
	/** @return latest stop in this commute */
	public CommutePoint getArrival() {
		return stops.last();	// Arrival is the latest
	}
	
	/** @return a copy of all stops, sorted by time ascending */
	public Set<CommutePoint> getStops() {
		TreeSet<CommutePoint> returnSet = new TreeSet<>();
		
		for (CommutePoint stop : stops)
			returnSet.add(new CommutePoint(stop));	// Add a copy
		
		return returnSet;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((stops == null) ? 0 : stops.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Commute))
			return false;
		Commute other = (Commute) obj;
		if (day != other.day)
			return false;
		if (stops == null) {
			if (other.stops != null)
				return false;
		} else if (!stops.equals(other.stops))
			return false;
		return true;
	}
}
