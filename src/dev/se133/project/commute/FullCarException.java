package dev.se133.project.commute;

public class FullCarException extends Exception{
	//TODO Serial version UID, IDK what that is

	/**
	 * Constructs a custom instance of this exception.
	 * @param invalidTime invalid input time
	 * @param minTime minimum valid time
	 * @param maxTime maximum valid time
	 */
	public FullCarException(int numberOfSeats) {
		super(buildMessage(numberOfSeats));
	}
	
	private static String buildMessage(int numberOfSeats) {
		String message = "This vehicle has reached it's maximum capacity of " + numberOfSeats + " number of seats.";
		
		return message;
	}
}
