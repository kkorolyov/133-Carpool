package dev.se133.project.carpool;

import dev.se133.project.state.State;

public class CarpoolState implements State {
	private final String stateName;
	
	protected CarpoolState(String stateName) {
		this.stateName = stateName;
	}
	
	
	@Override
	public String getStateName() {
		// TODO Auto-generated method stub
		return stateName;
	}
	
	public String toString() {
		return getStateName();
	}
	
	/**
	 * The state a carpool takes while not ready to depart.
	 */
	public static class Loading extends CarpoolState {
		private static final String LOADING_STATE_NAME = "LOADING";
		
		/**
		 * Constructs a new {@code LOADING} state.
		 */
		public Loading() {
			super(LOADING_STATE_NAME);
		}
	}
	
	/**
	 * The state a carpool takes when it is ready to depart.
	 */
	public static class Ready extends CarpoolState {
		private static final String READY_STATE_NAME = "READY";
		
		/**
		 * Constructs a new {@code READY} state.
		 */
		public Ready() {
			super(READY_STATE_NAME);
		}
	}
	
	public static class Departed extends CarpoolState {
		private static final String DEPARTED_STATE_NAME = "DEPARTED";
		
		public Departed() {
			super(DEPARTED_STATE_NAME);
		}
		
	}
	
	public static class OnRoute extends CarpoolState {
		private static final String ON_ROUTE_STATE_NAME = "ON_ROUTE";
		
		public OnRoute() {
			super(ON_ROUTE_STATE_NAME);
		}
		
	}
	
	public static class Arrived extends CarpoolState {
		private static final String ARRIVED_STATE_NAME = "ARRIVED";
		
		public Arrived() {
			super(ARRIVED_STATE_NAME);
		}
		
	}

}
