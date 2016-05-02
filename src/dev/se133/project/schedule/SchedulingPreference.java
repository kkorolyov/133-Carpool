package dev.se133.project.schedule;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import dev.se133.project.member.Member;

/**
 * Describes a set of members a single member prefers to be paired with.
 */
public class SchedulingPreference {

	private Map<Member, Member[]> matchings = new HashMap<>();
	
	/**
	 * Adds a matching.
	 * @param base member with preference
	 * @param preferred all members base members prefers to be matched with
	 */
	public void add(Member base, Member[] preferred) {
		matchings.put(base, preferred);
	}
	/**
	 * Removes a base member's matching preference.
	 * @param base base member to remove preference of
	 * @return removed preference, or {@code null} if did not exist
	 */
	public Member[] remove(Member base) {
		return matchings.remove(base);
	}
	
	/**
	 * Clears all preferences.
	 */
	public void clear() {
		matchings.clear();
	}
	
	/** @return all members with preferences */
	public Set<Member> getBaseMembers() {
		return matchings.keySet();
	}
}
