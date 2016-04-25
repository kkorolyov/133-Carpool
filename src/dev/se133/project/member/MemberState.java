package dev.se133.project.member;

import dev.se133.project.carpool.Carpool;
import dev.se133.project.member.preferences.CommuteSchedule;
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
	 * Sets a member's commuteTimes attribute
	 * @param context member in question
	 * @param newCommuteTimes new commute times to set
	 * @throws IllegalStateException if this method cannot be handled by the current state
	 */
	public void setCommuteTimes(Member context, CommuteSchedule newCommuteTimes) {
		throw new IllegalStateException("Commute times may not be set in the current state: " + getStateName());
	}
	
	/**
	 * Responds in the event of a carpool dispatch.
	 * @param context member in question
	 * @param dispatchedCarpool dispatched carpool
	 */
	public void dispatched(Member context, Carpool dispatchedCarpool) {
		throw new IllegalStateException("Member's carpool should not be dispatched in the current state: " + getStateName());
	}
	
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
		
		@Override
		public void setCommuteTimes(Member context, CommuteSchedule newCommuteTimes) {
			context.setCommuteTimesState(newCommuteTimes);
		}
		
		@Override
		public void dispatched(Member context, Carpool dispatchedCarpool) {
			Member dispatchedCarpoolDriver = dispatchedCarpool.getCar().getDriver();
			
			if (dispatchedCarpoolDriver.equals(context))
				context.setState(new Driving());
			else if (dispatchedCarpool.getCar().contains(context))
				context.setState(new Riding());
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
		
	}
	/**
	 * The {@code RIDING} state for a member.
	 */
	public static class Riding extends MemberState {
		private static final String RIDING_STATE_NAME = "RIDING";
		
		/**
		 * Constructs a new {@code RIDING} state for a member.
		 */
		public Riding() {
			super(RIDING_STATE_NAME);
		}
		
	}
}
