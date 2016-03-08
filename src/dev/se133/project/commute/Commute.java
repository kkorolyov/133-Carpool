package dev.se133.project.commute;

import java.util.Set;
import java.util.TreeSet;

/**
 * A one-way commute consisting of an infinite set of {@code CommutePoint} objects.
 * @see CommutePoint
 */
public class Commute {
	private TreeSet<CommutePoint> stops = new TreeSet<>();	// Ordered by time ascending
	
	/**
	 * Constructs an empty commute with no stops.
	 */
	public Commute() {
		this(null);
	}
	/**
	 * Constructs a commute with an initial set of stops.
	 * @param stops all stops in the commute
	 */
	public Commute(Set<CommutePoint> stops) {
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
	
	/** @return earliest stop in this commute */
	public CommutePoint getStart() {
		return stops.first();
	}
	/** @return final stop in this commute */
	public CommutePoint getEnd() {
		return stops.last();
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
		if (stops == null) {
			if (other.stops != null)
				return false;
		} else if (!stops.equals(other.stops))
			return false;
		return true;
	}
	
	public String toString() {
		String returnStatement = "";
		int i = 0;
		for(CommutePoint stop : stops) {
			returnStatement += "Stop " + i++ + ": " + stop.getAddress() + "\n";
		}
		return returnStatement;
	}
}
