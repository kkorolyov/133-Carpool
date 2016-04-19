package dev.se133.project.carpool;

import dev.se133.project.car.Car;
import dev.se133.project.commute.Commute;
import dev.se133.project.state.State;

public abstract class CarpoolState implements State {
	private final String stateName;
	
	protected CarpoolState(String stateName) {
		this.stateName = stateName;
	}
	
	/**
	 * Dispatches a carpool.
	 * Behavior is dependent on the current state.
	 * @param context carpool to dispatch
	 * @throws IllegalStateException if this method cannot be handled by the current state
	 */
	public void dispatch(Carpool context) {
		throw new IllegalStateException("May not be dispatched in this state: " + getStateName());
	}
	
	/**
	 * Sets a carpool's commute.
	 * Behavior is dependent on the current state.
	 * @param context carpool to set commute of
	 * @param newCommute new commute to set
	 * @throws IllegalStateException if this method cannot be handled by the current state
	 */
	public void setCommute(Carpool context, Commute newCommute) {
		throw new IllegalStateException("Commute may not be set in this state: " + getStateName());
	}
	/**
	 * Sets a carpool's car.
	 * Behavior is dependent on the current state.
	 * @param context carpool to set car of
	 * @param newCar new car to set
	 * @throws IllegalStateException if this method cannot be handled by the current state
	 */
	public void setCar(Carpool context, Car newCar) {
		throw new IllegalStateException("Car may not be set in this state: " + getStateName());
	}
	
	@Override
	public String getStateName() {
		return stateName;
	}
	
	@Override
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
		
		@Override
		public void setCommute(Carpool context, Commute newCommute) {
			context.stateSetCommute(newCommute);
			
			testReady(context);
		}
		@Override
		public void setCar(Carpool context, Car newCar) {
			context.stateSetCar(newCar);
			
			testReady(context);
		}
		
		private static void testReady(Carpool context) {
			if (isReady(context))
				context.setState(new Ready());
		}
		private static boolean isReady(Carpool context) {
			return (context.getCommute() != null && context.hasDriver());
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
		
		@Override
		public void dispatch(Carpool context) {
			// TODO
		}

		@Override
		public void setCommute(Carpool context, Commute newCommute) {
			context.stateSetCommute(newCommute);
			
			testLoading(context);
		}
		@Override
		public void setCar(Carpool context, Car newCar) {
			context.stateSetCar(newCar);
			
			testLoading(context);
		}
		
		private static void testLoading(Carpool context) {
			if (isLoading(context))
				context.setState(new Loading());
		}
		private static boolean isLoading(Carpool context) {
			return (context.getCommute() == null || !context.hasDriver());
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
