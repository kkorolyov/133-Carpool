package dev.se133.project.member.preferences;

import java.util.Set;
import java.util.TreeSet;

import dev.se133.project.commute.Stop;
import dev.se133.project.commute.Time;

/**
 * Sorted set of {@code Stop} objects.
 */
public class CommuteSchedule {
	private Set<Stop> schedule = new TreeSet<>();
	
	/**
	 * Returns all stops between a range of times.
	 * @param start start of time range
	 * @param end end of time range
	 * @return all stops within time range
	 */
	public Set<Stop> getStops(Time start, Time end) {
		Set<Stop> toReturn = new TreeSet<>();
		
		for (Stop stop : schedule) {
			if (isBetween(stop, start, end))
				toReturn.add(stop);
		}
		return toReturn;
	}
	private static boolean isBetween(Stop stop, Time start, Time end) {
		Time stopTime = stop.getTime();
		
		return (stopTime.compareTo(start) >= 0 && stopTime.compareTo(end) <= 0);
	}
	
	/**
	 * Adds a stop to this schedule.
	 * @param toAdd stop to add
	 * @return {@code true} if this schedule did not already contain the specified stop
	 */
	public boolean add(Stop toAdd) {
		return schedule.add(toAdd);
	}
	/**
	 * Removes a stop from this schedule.
	 * @param toRemove stop to remove
	 * @return {@code true} if this schedule contained the specified stop
	 */
	public boolean remove(Stop toRemove) {
		return schedule.remove(toRemove);
	}
	
	/**
	 * Clears this schedule.
	 */
	public void clear() {
		schedule.clear();
	}
}
