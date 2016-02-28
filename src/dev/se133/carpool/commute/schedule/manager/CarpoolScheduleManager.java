package dev.se133.carpool.commute.schedule.manager;

import java.util.Set;
import java.util.TreeSet;

import dev.se133.carpool.commute.Carpool;

/**
 * Centralized manager for carpool scheduling.
 */
public class CarpoolScheduleManager {
	private static CarpoolScheduleManager instance;
	
	private Set<Carpool> carpools = new TreeSet<>();
	
	/**
	 * Returns the sole manager for the current runtime.
	 * @return manager
	 */
	public static CarpoolScheduleManager getManager() {
		if (instance == null)
			instance = new CarpoolScheduleManager();
		return instance;
	}
	private CarpoolScheduleManager() {
		// Private constructor
	}
}
