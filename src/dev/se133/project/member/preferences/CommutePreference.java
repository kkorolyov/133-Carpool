package dev.se133.project.member.preferences;

import dev.se133.project.commute.Time;
import dev.se133.project.commute.Time.Day;

/**
 * A set of 4 time preference describing an overall commuting preference.
 * The 4 time preferences should be a set such that: <br>
 * {@code toDestination <= atDestination <= toSource <= atSource}, <br>
 * where {@code A <= B} means that time {@code A} occurs before or at the same point in time as time {@code B}.
 */
public class CommutePreference {
	@SuppressWarnings("javadoc")
	public static final byte	TO_DESTINATION = 0,
														AT_DESTINATION = 1,
														TO_SOURCE = 2,
														AT_SOURCE = 3;
	private Time 	toDestination,
								atDestination,
								toSource,
								atSource;
	
	/**
	 * Constructs a commute preference with all individual time preferences predefined.
	 * @param toDestination preferred time to leave for a destination
	 * @param atDestination preferred time to arrive at a destination
	 * @param toSource preferred time to leave for the original source (home)
	 * @param atSource preferred time to arrive at the original source (home)
	 * @throws IllegalArgumentException if the specified parameters do not follow the criteria declared by the class
	 */
	public CommutePreference(Time toDestination, Time atDestination, Time toSource, Time atSource) {
		setToDestination(toDestination);
		setAtDestination(atDestination);
		setToSource(toSource);
		setAtSource(atSource);
	}
	
	/** @return day of the week of this commute preference's {@code toDestination} time */ 
	public Day getDay() {
		return toDestination.getDay();
	}
	
	/**
	 * Returns the time preference associated with the specified marker.
	 * This method expects a value from <br>
	 * <ul>
	 * <li> {@code CommutePreference.TO_DESTINATION}
	 * <li> {@code CommutePreference.AT_DESTINATION}
	 * <li> {@code CommutePreference.TO_SOURCE}
	 * <li> {@code CommutePreference.AT_SOURCE}
	 * </ul>
	 * @param timePreference unique marker of the time preference to retrieve
	 * @return appropriate time preference
	 */
	public Time getTime(int timePreference) {
		Time toReturn = null;
		
		switch (timePreference) {
		case (TO_DESTINATION):
			toReturn = toDestination;
			break;
		case(AT_DESTINATION):
			toReturn = atDestination;
			break;
		case(TO_SOURCE):
			toReturn = toSource;
			break;
		case(AT_SOURCE):
			toReturn = atSource;
			break;
		}
		return toReturn;
	}
	
	private void setToDestination(Time newToDestination) {
		this.toDestination = newToDestination;
	}
	private void setAtDestination(Time newAtDestination) {
		if (newAtDestination.compareTo(toDestination) < 0)
			throw new IllegalArgumentException();
		
		this.atDestination = newAtDestination;
	}
	private void setToSource(Time newToSource) {
		if (newToSource.compareTo(atDestination) < 0)
			throw new IllegalArgumentException();
		
		this.toSource = newToSource;
	}
	private void setAtSource(Time newAtSource) {
		if (newAtSource.compareTo(toSource) < 0)
			throw new IllegalArgumentException();
		
		this.atSource = newAtSource;
	}
}
