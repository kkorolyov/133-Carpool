package dev.se133.project.commutebuilder;

import dev.se133.project.commute.Commute;
import dev.se133.project.commute.Stop;
import dev.se133.project.map.AddressMap;
import dev.se133.project.member.car.Car;
import dev.se133.project.member.car.NoDriverException;

/**
 * Builds a commute based on a specified context.
 */
public interface CommuteBuilder {
	/**
	 * Creates an appropriate commute route for a car.
	 * If the builder is unable to create a commute meeting the specified constraints, the builder returns {@code null}.
	 * @param map map to use for routing
	 * @param car the car intending to commute
	 * @param start the commute's departure point
	 * @param end the commute's arrival point
	 * @return a custom commute based on the specified parameters, or {@code null} if unable to meet constraints
	 * @throws NoDriverException if the commuting car has no driver
	 */
	Commute buildCommute(AddressMap map, Car car, Stop start, Stop end) throws NoDriverException;
}
