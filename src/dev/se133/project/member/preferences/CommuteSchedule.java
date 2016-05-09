package dev.se133.project.member.preferences;

import java.util.Set;
import java.util.TreeSet;

import dev.se133.project.commute.Address;
import dev.se133.project.commute.Stop;
import dev.se133.project.commute.Time;

/**
 * Sorted set of {@code Stop} objects.
 */
public class CommuteSchedule {
	private final int SECONDS_IN_WEEK = 7 * 24 * 60 * 60;;
	private Set<Stop> schedule = new TreeSet<>();
	
	/**
	 * Returns all stops between a range of times.
	 * @param start start of time range
	 * @param end end of time range
	 * @return all stops within time range
	 */
	public Set<Stop> getStops(Time start, Time end) {
		return getStops(start, end, null);
	}
	/**
	 * Returns all stops between a range of times and at the specified destination address.
	 * @param start start of time range
	 * @param end end of time range
	 * @param destination destination to filter by, if {@code null}, will be ignored
	 * @return all stops within time range and at specified destination address.
	 */
	public Set<Stop> getStops(Time start, Time end, Address destination) {
		Set<Stop> toReturn = new TreeSet<>();
		
		for (Stop stop : schedule) {
			if (isBetween(stop, start, end) && occursAt(stop, destination))
				toReturn.add(stop);
		}
		return toReturn;
	}
	private static boolean isBetween(Stop stop, Time start, Time end) {
		Time stopTime = stop.getTime();
		
		return (stopTime.compareTo(start) >= 0 && stopTime.compareTo(end) <= 0);
	}
	private static boolean occursAt(Stop stop, Address destination) {
		Address stopAddress = stop.getAddress();
		
		return (destination == null || stopAddress.equals(destination));
	}
	
	/**
	 * Adds a weekly-repeated stop.
	 * @param baseStop first iteration of stop to add
	 * @param extraWeek number of weeks to repeat stop, if {@code 0} only the base stop is added 
	 * @return number of new stops added
	 */
	public int addWeekly(Stop baseStop, int extraWeek) {
		int stopsAdded = 1;
		
		for (int i = 0; i <= extraWeek; i++) {
			Time currentTime = Time.timeAfter(baseStop.getTime(), i * SECONDS_IN_WEEK);
			Address currentAddress = baseStop.getAddress();
			
			Stop currentStop = new Stop(currentTime, currentAddress);
			
			if(add(currentStop))
				stopsAdded++;
		}
		return stopsAdded;
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
