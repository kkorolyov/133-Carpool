package dev.se133.project.function;

import dev.se133.project.commute.Car;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.CommutePoint;

/**
 * Builds a commute based on a specified context.
 */
public interface CommuteBuilder {
	/**
	 * Creates an appropriate commute for a car.
	 * @param car the car intending to commute
	 * @param departure the commute's departure point
	 * @param arrival the commute's arrival point
	 * @return a custom commute based on the specified parameters
	 */
	Commute buildCommute(Car car, CommutePoint departure, CommutePoint arrival);
}
