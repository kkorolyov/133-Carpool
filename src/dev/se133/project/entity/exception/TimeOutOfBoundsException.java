package dev.se133.project.entity.exception;

/**
 * Thrown when an input hour or minute is out of bounds.
 */
public class TimeOutOfBoundsException extends Exception {
	private static final long serialVersionUID = -7599141951594173490L;

	/**
	 * Constructs a custom instance of this exception.
	 * @param invalidTime invalid input time
	 * @param minTime minimum valid time
	 * @param maxTime maximum valid time
	 */
	public TimeOutOfBoundsException(int invalidTime, int minTime, int maxTime) {
		super(buildMessage(invalidTime, minTime, maxTime));
	}
	
	private static String buildMessage(int invalidTime, int minTime, int maxTime) {
		String message = invalidTime + ": must be between " + minTime + "-" + maxTime + " inclusive";
		
		return message;
	}
}
