package dev.se133.project.entity.member;

/**
 * A member-specific state.
 */
public abstract class MemberState implements State {
	private final int stateId;
	private final String stateName;
	
	protected MemberState(int stateId, String stateName) {
		this.stateId = stateId;
		this.stateName = stateName;
	}
	
	// TODO Handling methods
	
	@Override
	public int getStateId() {
		return stateId;
	}
	@Override
	public String getStateName() {
		return stateName;
	}
	
	/**
	 * The Passenger state for a member.
	 */
	public static class Passenger extends MemberState {
		private static final int id = 0;
		private static final String name = "Passenger";
		
		/**
		 * Constructs a new passenger state.
		 */
		public Passenger() {
			super(id, name);
		}
	}
	
	/**
	 * The Driver state for a member.
	 */
	public static class Driver extends MemberState {
		private static final int id = 1;
		private static final String name = "Driver";
		
		/**
		 * Constructs a new passenger state.
		 */
		public Driver() {
			super(id, name);
		}
	}
}
