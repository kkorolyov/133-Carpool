package dev.se133.project.commute;

/**
 * Listens to interesting {@code Carpool} events.
 * @see Carpool
 */
public interface CarpoolListener {
	/**
	 * Notifies this listener that the carpool has hit a stop.
	 * @param stop the last-hit stop in question
	 */
	void hitStop(CommutePoint stop);
	
	/**
	 * Notifies this listener that the carpool has reached its final stop.
	 */
	void hitEnd();
}
