package dev.se133.project.member;

import dev.se133.project.member.car.Car;
import dev.se133.project.state.State;

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
	
	/**
	 * Returns the car most appropriate to the current state.
	 * @param context member this state describes
	 * @return appropriate car
	 */
	public abstract Car getCar(Member context);
	
	@Override
	public int getStateId() {
		return stateId;
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
	 * The state occurring when a member is not willing to drive and is not currently in a carpool.
	 */
	public static class Passenger extends MemberState {	// TODO Rename to Rider to match Riding?
		private static final int id = 0;
		private static final String name = "Passenger";
		
		/**
		 * Constructs a new passenger state.
		 */
		public Passenger() {
			super(id, name);
		}
		
		@Override
		public Car getCar(Member context) {
			return null;
		}
	}
	
	/**
	 * The state occurring when a member is willing to drive and is not currently in a carpool.
	 */
	public static class Driver extends MemberState {
		private static final int id = Passenger.id + 1;
		private static final String name = "Driver";
		
		/**
		 * Constructs a new driver state.
		 */
		public Driver() {
			super(id, name);
		}
		
		@Override
		public Car getCar(Member context) {
			context.setState(new Driving());
			return new Car(context.getGarage().getLargestVehicle().getCapacity(), context);	// Uses largest of member's vehicles
		}
	}
	
	/**
	 * The state occurring when a member is currently in a carpool as a passenger.
	 */
	public static class Riding extends MemberState {
		private static final int id = Driver.id + 1;
		private static final String name = "Riding";
		
		/**
		 * Constructs a 'riding' state.
		 */
		public Riding() {
			super(id, name);
		}

		@Override
		public Car getCar(Member context) {
			return context.getCurrentCarpool().getCar();
		}
	}
	
	/**
	 * The state occurring when a member is currently in a carpool as a driver.
	 */
	public static class Driving extends MemberState {
		private static final int id = Riding.id + 1;
		private static final String name = "Driving";
		
		/**
		 * Constructs a 'driving' state.
		 */
		public Driving() {
			super(id, name);
		}

		@Override
		public Car getCar(Member context) {
			return context.getCurrentCarpool().getCar();
		}
	}
}
