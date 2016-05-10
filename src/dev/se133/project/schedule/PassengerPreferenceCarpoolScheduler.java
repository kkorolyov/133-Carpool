package dev.se133.project.schedule;

import dev.se133.project.commute.Address;
import dev.se133.project.commute.Time;
import dev.se133.project.member.Member;

/**
 * Carpool scheduler which takes into consideration passengers' selection of drivers.
 */
public class PassengerPreferenceCarpoolScheduler extends BasicCarpoolScheduler {

	/**
	 * Constructs a new scheduler.
	 * @see CarpoolScheduler
	 */
	public PassengerPreferenceCarpoolScheduler(Member[] members, Time start, Time end, Address destination, SchedulingPreference preferences) {
		super(members, start, end, destination, preferences);
	}

	@Override
	public CarpoolSchedule schedule() {
		// TODO Auto-generated method stub
		return null;
	}
}
