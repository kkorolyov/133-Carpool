package dev.se133.project.member;

import dev.se133.project.car.Car;
import dev.se133.project.state.State;

/**
 * A member-specific state.
 */
public abstract class MemberState implements State {
	private final String stateName;
	
	protected MemberState(String stateName) {
		this.stateName = stateName;
	}
	
	/**
	 * Returns the car most appropriate to the current state.
	 * @param context member this state describes
	 * @return appropriate car
	 */
	public abstract Car getCar(Member context);
	
	@Override
	public String getStateName() {
		return stateName;
	}
	
	/**
	 * Returns the name of this state.
	 */
	@Override
	public String toString() {
		return getStateName();
	}
	
	/**
	 * The {@code IDLE} state for a member.
	 */
	public static class Idle extends MemberState {
		private static final String IDLE_STATE_NAME = "IDLE";
		
		/**
		 * Constructs a new {@code IDLE} state for a member.
		 */
		public Idle() {
			super(IDLE_STATE_NAME);
		}
		
		public Car getCar(Member context) {
			return null;	// Member is not in a carpool when in IDLE state
		}
	}
	/**
	 * The {@code DRIVING} state for a member.
	 */
	public static class Driving extends MemberState {
		private static final String DRIVING_STATE_NAME = "DRIVING";
		
		/**
		 * Constructs a new {@code DRIVING} state for a member.
		 */
		public Driving() {
			super(DRIVING_STATE_NAME);
		}
		
		public Car getCar(Member context) {
			return context.getCurrentCarpool().getCar();	// TODO May want to change
		}
	}
	/**
	 * The {@code RIDING} state for a member.
	 */
	public static class Riding extends MemberState {
		private static final String RIDING_STATE_NAME = "RIDING";
		
		/**
		 * Constructs a new {@code RIDING} state for a member.
		 */
		public Riding(Member describedMember) {
			super(RIDING_STATE_NAME);
		}
		
		public Car getCar(Member context) {
			return context.getCurrentCarpool().getCar();	// TODO May want to change
		}
	}
}
