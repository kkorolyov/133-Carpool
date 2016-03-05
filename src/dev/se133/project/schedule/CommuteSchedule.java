package dev.se133.project.schedule;

import dev.se133.project.commute.Commute;
import dev.se133.project.commute.Day;

/**
 * General commuting schedule interface.
 * Consists of a collection of unique, mutually-exclusive commutes.
 */
public interface CommuteSchedule {	// TODO Migrate to generic Schedule interface
	
	/** @return all scheduled commutes, sorted in ascending order by departure time */
	Commute[] getAllCommutes();
	/**
	 * Returns all scheduled commutes filtered for a single day of the week.
	 * @param day day of commutes
	 * @return scheduled commutes for the day, sorted in ascending order by departure time, or {@code null} if none scheduled
	 */
	Commute[] getAllCommutes(Day day);

	/**
	 * Schedules a new commute.
	 * @param commute new commute
	 */
	void scheduleCommute(Commute commute);
	/**
	 * Drops a previously-scheduled commute.
	 * @param commute commute to drop
	 * @return {@code true} if commute dropped successfully, {@code false} if no such commute was scheduled
	 */
	boolean dropCommute(Commute commute);
	/**
	 * Drops all scheduled commutes.
	 * @return all dropped commutes, sorted in ascending order by departure time
	 */
	Commute[] dropAllCommutes();
	/**
	 * Drops all commutes on a single day of the week.
	 * @param day day to clear
	 * @return all dropped commutes, sorted in ascending order by departure time
	 */
	Commute[] dropAllCommutes(Day day);
}
