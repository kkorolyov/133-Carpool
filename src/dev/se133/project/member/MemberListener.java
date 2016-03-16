package dev.se133.project.member;

/**
 * Allows for listening for interesting events cause by {@code Member} objects.
 * @see Member
 */
public interface MemberListener {
	/**
	 * Notifies this listener that a member's state has changed.
	 * @param newState the observed member's new state
	 */
	void stateChanged(MemberState newState);
}
