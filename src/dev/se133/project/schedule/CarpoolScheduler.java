package dev.se133.project.schedule;

/**
 * Creates a carpool schedule.
 */
public interface CarpoolScheduler {

	/**
	 * Creates a carpool schedule using the specified properties.
	 * @return appropriate carpool schedule
	 */
	CarpoolSchedule schedule();
}
