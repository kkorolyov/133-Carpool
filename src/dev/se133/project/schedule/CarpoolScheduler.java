package dev.se133.project.schedule;

import java.util.Arrays;
import java.util.Comparator;

import dev.se133.project.commute.Time;
import dev.se133.project.commute.Time.Day;
import dev.se133.project.member.Member;
import dev.se133.project.member.preferences.CommutePreference;
import dev.se133.project.member.preferences.CommuteSchedule;

/**
 * Creates a carpool schedule.
 */
public abstract class CarpoolScheduler {
	
	Member[] members;
	Time 	start,
				end;
	SchedulingPreference preferences;
	
	/**
	 * Constructs a new carpool scheduler for the specified properties.
	 * @param members all members to schedule
	 * @param start start point of schedule range
	 * @param end end point of schedule ranges
	 * @param preferences optional additional scheduling preferences
	 */
	public CarpoolScheduler(Member[] members, Time start, Time end, SchedulingPreference preferences) {
		this.members = members;
		this.start = start;
		this.end = end;
		this.preferences = preferences;
	}

	/**
	 * Creates a carpool schedule using the specified properties.
	 * @return appropriate carpool schedule
	 */
	public abstract CarpoolSchedule schedule();
	
	static void sortMembers(Member[] members) {
		Arrays.sort(members, buildMemberPreferenceComparator());
	}
	static Comparator<Member> buildMemberPreferenceComparator() {
		return new Comparator<Member>() {

			@Override
			public int compare(Member o1, Member o2) {
				CommuteSchedule o1Schedule = o1.getCommuteTimes(),
												o2Schedule = o1.getCommuteTimes();
				Day o1EarliestDay = o1Schedule.getEarliest(),
						o2EarliestDay = o2Schedule.getEarliest();
				Time 	o1EarliestTime = o1Schedule.getPreference(o1EarliestDay).getTime(CommutePreference.TO_DESTINATION),
							o2EarliestTime = o2Schedule.getPreference(o2EarliestDay).getTime(CommutePreference.TO_DESTINATION);
				
				return o1EarliestTime.compareTo(o2EarliestTime);
			}
		};
	}
}
