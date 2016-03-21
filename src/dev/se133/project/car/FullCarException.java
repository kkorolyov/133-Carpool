package dev.se133.project.car;

/**
 * Exception thrown when adding a new inhabitant to a {@code Car} would overflow its capacity.
 */
public class FullCarException extends RuntimeException{
	private static final long serialVersionUID = -3247087105986782821L;

	/**
	 * Constructs a custom instance of this exception.
	 */
	public FullCarException(int numberOfSeats) {
		super(buildMessage(numberOfSeats));
	}
	
	private static String buildMessage(int numberOfSeats) {
		String message = "This vehicle has reached it's maximum capacity of " + numberOfSeats + " number of seats.";
		
		return message;
	}
}
