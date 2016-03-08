package dev.se133.project.router;

import dev.se133.project.commute.Car;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.CommutePoint;
import dev.se133.project.map.AddressMap;

/**
 * Builds a commute based on a specified context.
 */
public interface CommuteBuilder {
	/**
	 * Creates an appropriate commute route for a car.
	 * @param map map to use for routing
	 * @param car the car intending to commute
	 * @param departure the commute's departure point
	 * @param arrival the commute's arrival point
	 * @return a custom commute based on the specified parameters
	 */
	Commute buildCommute(AddressMap map, Car car, CommutePoint departure, CommutePoint arrival);
}
