package dev.se133.project.commute;

import dev.se133.project.member.Member;

/**
 * Listens to interesting {@code Carpool} events.
 * @see Carpool
 */
public interface CarpoolListener {
	/**
	 * Notifies this listener that the carpool has hit a stop.
	 * @param carpool the carpool object hitting the stop
	 */
	void hitStop(Carpool carpool);
	
	/**
	 * Notifies this listener that the carpool has reached its final stop.
	 * @param carpool the carpool object hit the end of its commute
	 */
	void hitEnd(Carpool carpool);
	
	
}
