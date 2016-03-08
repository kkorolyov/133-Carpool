package dev.se133.project.scheduler;

import java.util.HashMap;
import java.util.Map;

import dev.se133.project.member.Member;
import dev.se133.project.schedule.CommuteSchedule;

/**
 * Centralized manager for commute schedules for members.
 */
public class CommuteScheduleManager {
	private static CommuteScheduleManager instance;
	
	private final Map<Member, CommuteSchedule> schedules = new HashMap<>();	// Separate commute schedule for each member
	
	/**
	 * Returns the sole manager for the current runtime.
	 * @return manager
	 */
	public static CommuteScheduleManager getManager() {
		if (instance == null)
			instance = new CommuteScheduleManager();
		return instance;
	}
	private CommuteScheduleManager() {
		// Private constructor
	}
	
	// TODO Management methods
}
