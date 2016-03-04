package dev.se133.project.entity.vehicle.exception;

/**
 * Thrown when an invalid vehicle year is input.
 */
public class YearOutOfBoundsException extends Exception {
	private static final long serialVersionUID = 8122900821745812575L;

	/**
	 * Constructs a custom instance of this exception.
	 * @param year out of bounds year
	 * @param minYear valid minimum year
	 * @param maxYear valid maximum year
	 */
	public YearOutOfBoundsException(int year, int minYear, int maxYear) {
		super(buildMessage(year, minYear, maxYear));
	}
	
	private static String buildMessage(int year, int minYear, int maxYear) {
		String message = "Invalid vehicle year: " + year + "; must be between " + minYear + "-" + maxYear + " inclusive";
		
		return message;
	}
}
