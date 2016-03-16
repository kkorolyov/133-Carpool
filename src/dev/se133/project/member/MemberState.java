package dev.se133.project.member;

import dev.se133.project.commute.Car;
import dev.se133.project.commute.FullCarException;
import dev.se133.project.commute.NoDriverException;

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
	 * Creates a new car with the specified member preloaded into it.
	 * @param context member to load into car
	 * @return new car with member loaded into it
	 */
	public abstract Car makeCar(Member context);
	
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
		
		@Override
		public Car makeCar(Member context) {
			Car newCar = new Car(context.getGarage().getVehicles().iterator().next().getCapacity());
			
			try {
				newCar.addPassenger(context);
			} catch (FullCarException | NoDriverException e) {
				e.printStackTrace();
			}
			return newCar;
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
		
		/** Sets context member as car driver. */
		@Override
		public Car makeCar(Member context) {
			Car newCar = new Car(context.getGarage().getVehicles().iterator().next().getCapacity());
			
			try {
				newCar.addPassenger(context);
			} catch (FullCarException | NoDriverException e) {
				e.printStackTrace();
			}
			newCar.setDriver(context);
			
			return newCar;
		}
	}
}
