package dev.se133.project.carpool;

import dev.se133.project.commute.Stop;

/**
 * Listens to interesting {@code Carpool} events.
 * @see Carpool
 */
public interface CarpoolListener {
	/**
	 * Notifies this listener that the carpool has been dispatched.
	 * @param context the carpool that has been dispatched
	 */
	void dispatched(Carpool context);
	
	/**
	 * Notifies this listener that the carpool has hit a stop.
	 * @param currentStop the current stop of the carpool
	 */
	void hitStop(Stop currentStop);
	
	/**
	 * Notifies this listener that the carpool has reached its final stop.
	 * @param endStop the current and last stop of this carpool
	 */
	void hitEnd(Stop endStop);
}
