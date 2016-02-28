package dev.se133.carpool.member.state;

/**
 * The passenger state for a member.
 */
public class StatePassenger extends MemberState {
	private static final int id = 0;
	private static final String name = "Passenger";
	
	/**
	 * Constructs a new passenger state.
	 */
	public StatePassenger() {
		super(id, name);
	}
}
