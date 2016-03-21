package dev.se133.project.commute;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * A set of unique stops.
 * @see Stop
 */
public class Commute implements Comparable<Commute> {
	private TreeSet<Stop> stops;
	private Iterator<Stop> stopIterator;
	private Stop currentStop;
	
	/**
	 * Constructs a commute with no stops.
	 */
	public Commute() {
		stops = new TreeSet<>();
	}
	
	/** @return true if this commute has a next stop */
	public boolean hasNextStop() {
		testIterator();
		
		return stopIterator.hasNext();
	}
	/**
	 * Advances this commute to the next stop and returns that stop.
	 * If the commute has no next stop, returns the current stop.
	 * @return next stop or current stop if no next stop
	 */
	public Stop nextStop() {
		if (hasNextStop())
			currentStop = stopIterator.next();
		
		return currentStop;
	}
	
	private void testIterator() {
		if (stopIterator == null)
			stopIterator = stops.iterator();
	}
	
	/**
	 * Adds a new stop to this commute if an equal stop is not already present.
	 * @param toAdd stop to add
	 * @return {@code true} if stop successfully added
	 */
	public boolean addStop(Stop toAdd) {
		return stops.add(toAdd);
	}
	/**
	 * Removes a stop from this commute.
	 * @param toRemove stop to remove
	 * @return {@code true} if stop successfully removed
	 */
	public boolean removeStop(Stop toRemove) {
		return stops.remove(toRemove);
	}
	
	/**
	 * Returns the earliest stop in this commute.
	 * @return earliest stop, or {@code null} if no stops
	 */
	public Stop getStart() {
		return stops.first();
	}
	/**
	 * Returns the latest stop in this commute.
	 * @return latest stop, or {@code null} if no stops
	 */
	public Stop getEnd() {
		return stops.last();
	}
	
	/**
	 * Returns the current stop in this commute.
	 * This method depends on {@link #nextStop()}.
	 * @return this commute's current stop, or {@code null} if this commute has not yet called {@link #nextStop()}
	 */
	public Stop getCurrent() {
		return currentStop;
	}
	
	/**
	 * Returns a set of all stops in this commute.
	 * @return all stops
	 */
	public Set<Stop> getStops() {
		return stops;
	}
	
	/**
	 * Returns the number of stops in this commute.
	 * @return number of stops;
	 */
	public int getNumStops() {
		return stops.size();
	}
	
	/**
	 * Clears all stops from this commute.
	 */
	public void clear() {
		stops.clear();
	}
	
	/**
	 * Compares by earliest stop.
	 */
	@Override
	public int compareTo(Commute o) {
		return getStart().compareTo(o.getStart());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentStop == null) ? 0 : currentStop.hashCode());
		result = prime * result	+ ((stopIterator == null) ? 0 : stopIterator.hashCode());
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
		if (currentStop == null) {
			if (other.currentStop != null)
				return false;
		} else if (!currentStop.equals(other.currentStop))
			return false;
		if (stopIterator == null) {
			if (other.stopIterator != null)
				return false;
		} else if (!stopIterator.equals(other.stopIterator))
			return false;
		if (stops == null) {
			if (other.stops != null)
				return false;
		} else if (!stops.equals(other.stops))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder("Commute with " + getNumStops() + " stops\n");
		
		int stopCounter = 0;
		for (Stop stop : stops) {
			toStringBuilder.append("\tStop " + ++stopCounter + ": " + stop);
			
			if (stopCounter < getNumStops())
				toStringBuilder.append("\n");
		}
		return toStringBuilder.toString();
	}
}
