package dev.se133.project.commute.builder;

import java.util.Set;

import dev.se133.project.car.Car;
import dev.se133.project.car.NoDriverException;
import dev.se133.project.commute.*;
import dev.se133.project.map.AddressMap;
import dev.se133.project.member.Member;

/**
 * A {@code CommuteBuilder} implementation which uses a greedy strategy to build a commute.
 */
public class GreedyCommuteBuilder implements CommuteBuilder {

	@Override
	public Commute buildCommute(AddressMap map, Car car, Stop start, Stop end) throws NoDriverException {
		if (car.getDriver() == null)	// Cannot commute without a driver
			throw new NoDriverException();
		
		Commute commute = new Commute();
		
		try {
			commute.addStop(start);	// Add start point to commute
			commute.addStop(end);	// Add end point to commute
			
			Set<Member> allInhabitants = car.getInhabitants();	// Returned set is a copy, ok to mutate
			allInhabitants.remove(car.getDriver());	// No need to route to driver
			
			Stop lastStop = start;	// Start routing from 1st point = start
			
			while (!allInhabitants.isEmpty()) {
				double minDistance = Double.MAX_VALUE;	// No distance in commute should be greater than this
				Member minMember = null;
				
				for (Member inhabitant : allInhabitants) {
					double currentDistance = map.getDistance(lastStop.getAddress(), inhabitant.getAddress());
					if (currentDistance < minDistance) {
						minDistance = currentDistance;
						minMember = inhabitant;
					}
				}
				commute.addStop(lastStop = new Stop(Time.timeAfter(lastStop.getTime(), (int) minDistance + 1), minMember.getAddress()));
				allInhabitants.remove(minMember);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commute;
	}
}
