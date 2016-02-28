package dev.se133.carpool.member.state;

/**
 * Slightly more concrete member state.
 */
public abstract class AbstractMemberState implements MemberState {
	private final int stateId;
	private final String stateName;
	
	protected AbstractMemberState(int stateId, String stateName) {
		this.stateId = stateId;
		this.stateName = stateName;
	}
	
	/** @return state-specified id */
	public int getStateId() {
		return stateId;
	}
	/** @return state-specific name */
	public String getStateName() {
		return stateName;
	}
}
