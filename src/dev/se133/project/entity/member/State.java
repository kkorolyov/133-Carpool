package dev.se133.project.entity.member;

/**
 * General interface for a state.
 */
public interface State {
	/** @return state-specific id */
	int getStateId();
	
	/** @return state-specific name */
	String getStateName();
}
