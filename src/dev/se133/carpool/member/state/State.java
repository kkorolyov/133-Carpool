package dev.se133.carpool.member.state;

/**
 * General interface for a state.
 */
public interface State {
	/** @return state-specific id */
	int getStateId();
	
	/** @return state-specific name */
	String getStateName();
}
