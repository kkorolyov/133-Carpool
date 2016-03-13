package dev.se133.project.pool;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import dev.se133.project.member.Member;
import dev.se133.project.member.MemberState;
import dev.se133.project.schedule.CommuteSchedule;

/**
 * Central collection of all know members.
 */
public class MemberPool {
	private Map<CommuteSchedule, Member> 	drivers = new TreeMap<>(),	// Members sorted by commute start
																				passengers = new TreeMap<>();
	
	/**
	 * Adds a member to the pool.
	 * @param member member to add
	 */
	public void addMember(Member member) {
		Map<CommuteSchedule, Member> map = (member.getState() instanceof MemberState.Driver) ? drivers : passengers;	// Get correct map
		
		addMember(map, member);
	}
	private static void addMember(Map<CommuteSchedule, Member> map, Member member) {
		map.put(member.getPreferredCommutes(), member);
	}
	
	/** @return all drivers in pool, sorted by commute start time */
	public Set<Member> getDrivers() {
		return getMembers(drivers);
	}
	/** @return all passengers in pool, sorted by commut start time */
	public Set<Member> getPassengers() {
		return getMembers(passengers);
	}
	private static Set<Member> getMembers(Map<CommuteSchedule, Member> map) {
		return new TreeSet<Member>(map.values());
	}
}
