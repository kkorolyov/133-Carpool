package dev.se133.project.schedule;

import dev.se133.project.commute.Time;
import dev.se133.project.member.Member;

/**
 * Scheduler which does not support preferences.
 */
public class BasicCarpoolScheduler extends CarpoolScheduler {

	/**
	 * Constructs a new scheduler.
	 * @see CarpoolScheduler
	 */
	public BasicCarpoolScheduler(Member[] members, Time start, Time end, SchedulingPreference preferences) {
		super(members, start, end, preferences);
	}

	@Override
	public CarpoolSchedule schedule() {
		CarpoolSchedule schedule = new CarpoolSchedule();
		
		sortMembers(members);	// Sort by earliest preference
		
		return schedule;
	}
}
