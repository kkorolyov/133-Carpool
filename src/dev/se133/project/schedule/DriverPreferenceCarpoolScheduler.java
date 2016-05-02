package dev.se133.project.schedule;

import dev.se133.project.commute.Time;
import dev.se133.project.member.Member;

/**
 * Carpool scheduler which takes into consideration drivers' selection of passengers.
 */
public class DriverPreferenceCarpoolScheduler extends CarpoolScheduler {

	/**
	 * Constructs a new scheduler.
	 * @see CarpoolScheduler
	 */
	public DriverPreferenceCarpoolScheduler(Member[] members, Time start, Time end, SchedulingPreference preferences) {
		super(members, start, end, preferences);
	}

	@Override
	public CarpoolSchedule schedule() {
		// TODO Auto-generated method stub
		return null;
	}
}
