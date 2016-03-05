package dev.se133.project.member;

/**
 * General interface for a state.
 */
public interface State {
	/** @return state-specific id */
	int getStateId();
	
	/** @return state-specific name */
	String getStateName();
}
