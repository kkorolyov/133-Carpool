package dev.se133.carpool.member.manager;

import java.util.HashMap;
import java.util.Map;

import dev.se133.carpool.member.Member;

/**
 * Centralized manager for all members.
 */
public class MemberManager {
	private static MemberManager instance;
	
	private Map<Integer, Member> members = new HashMap<>();
	
	/**
	 * Returns the sole manager for the current runtime.
	 * @return manager
	 */
	public static MemberManager getManager() {
		if (instance == null)
			instance = new MemberManager();
		return instance;
	}
	private MemberManager() {
		// Private constructor
	}
	
	// TODO Management methods
}
