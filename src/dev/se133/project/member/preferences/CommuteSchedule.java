package dev.se133.project.member.preferences;

import java.util.HashMap;
import java.util.Map;

import dev.se133.project.commute.Time;
import dev.se133.project.commute.Time.Day;

/**
 * Maintains a set of unique {@code CommutePreference} objects for each day of the week.
 * @see CommutePreference
 */
public class CommuteSchedule implements Comparable<CommuteSchedule> {
	private final Map<Day, CommutePreference> preferences = new HashMap<>();
	private final Map<Time, CommutePreference> preferencesDates = new HashMap<>();
	
	/**
	 * Constructs an empty schedule.
	 */
	public CommuteSchedule() {
		// No-arg constructor
	}
	
	/**
	 * Returns the unique commuting preference for a specified day of the week.
	 * @param dayOf day of preference to retrieve
	 * @return commute preference for the specified day of the week, or {@code null} if no set preference for that day
	 */
	public CommutePreference getPreference(Day dayOf) {
		return preferences.get(dayOf);
	}
	
	/**
	 * returns all of the commute preferences associated with member
	 * @return
	 */
	public Map<Time, CommutePreference> getPreferences() {
		return preferencesDates;
	}
	/**
	 * Adds a commute preference to this schedule.
	 * If the day of the new preference coincides with the day of a preference stored prior, that old preference will be overwritten by this new preference.
	 * @param toAdd new preference to add
	 */
	public void addPreference(CommutePreference toAdd) {
		preferences.put(toAdd.getDay(), toAdd);
		preferencesDates.put(new Time(), toAdd);
	}
	
	/**
	 * Adds a commute preference to this schedule with a specified number of weekly repeats.
	 * @param toAdd the commute preference to be added
	 * @param numOfWeeks the number of weeks the commute preference will be repeated
	 */
	public void addPreferenceRepeated(CommutePreference toAdd, Time time, int numOfWeeks) {
		Time nextWeek;
		
		for(int i = 0; i < numOfWeeks; i++) {
			nextWeek = Time.timeAfter(time, i * 604800);
			preferencesDates.put(nextWeek, toAdd);
		}
		
	}
	/**
	 * Removes the stored commute preference set for the specified day.
	 * @param dayOf day of preference to remove
	 * @return removed preference, or {@code null} if no such preference
	 */
	public CommutePreference removePreference(Day dayOf) {
		return preferences.remove(dayOf);
	}
	
	/**
	 * Clears all stored preferences.
	 */
	public void clear() {
		preferences.clear();
		preferencesDates.clear();
	}
	
	/** @return	earliest time in this schedule */
	public Time getEarliest() {
		for (Day day : Day.values()) {
			if (getPreference(day) != null)
				return getPreference(day).getTime(CommutePreference.TO_DESTINATION);
		}
		return null;
	}

	@Override
	public int compareTo(CommuteSchedule o) {
		
		return 0;
	}
}
