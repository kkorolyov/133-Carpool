package dev.se133.project.member.factory;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import dev.se133.project.member.Member;
import dev.se133.project.member.preferences.CommuteSchedule;

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
		map.put(member.getCommuteTimes(), member);
	}
	
	/** @return copy of all drivers in pool, sorted by commute start time */
	public TreeSet<Member> getDrivers() {
		return getMembers(drivers);
	}
	/** @return copy of all passengers in pool, sorted by commute start time */
	public TreeSet<Member> getPassengers() {
		return getMembers(passengers);
	}
	private static TreeSet<Member> getMembers(Map<CommuteSchedule, Member> map) {
		return new TreeSet<Member>(map.values());
	}
}
