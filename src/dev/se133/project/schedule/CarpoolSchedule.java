package dev.se133.project.schedule;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import dev.se133.project.carpool.Carpool;

/**
 * A collection of Carpools.
 */
public class CarpoolSchedule implements Iterable<Carpool> {
	private Set<Carpool> carpools = new HashSet<>();
	
	/**
	 * Checks if this schedule contains the specified carpool
	 * @param toCheck carpool to check
	 * @return {@code true} if this schedule contains the specified carpool
	 */
	public boolean contains(Carpool toCheck) {
		return carpools.contains(toCheck);
	}
	/**
	 * Adds a carpool to this schedule
	 * @param toAdd carpool to add
	 * @return {@code true} if this schedule did not already contain an equal carpool
	 */
	public boolean add(Carpool toAdd) {
		return carpools.add(toAdd);
	}
	/**
	 * Removes a carpool from this schedule
	 * @param toRemove carpool to remove
	 * @return {@code true} if removal was successful
	 */
	public boolean remove(Carpool toRemove) {
		return carpools.remove(toRemove);
	}
	
	/**
	 * Returns an iterator over the carpools in this schedule.
	 */
	@Override
	public Iterator<Carpool> iterator() {
		return carpools.iterator();
	}
	
	@Override
	public String toString() {
		int counter = 0;
		StringBuilder toStringBuilder = new StringBuilder("Schedule with " + carpools.size() + " carpools:" + System.lineSeparator());
		
		for (Carpool carpool : carpools) {
			toStringBuilder.append(counter++ + ": " + carpool.toString()).append(System.lineSeparator());
		}
		return toStringBuilder.toString();
	}
}
