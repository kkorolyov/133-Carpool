package dev.se133.project.scheduler;

import dev.se133.project.commute.Car;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.CommutePoint;
import dev.se133.project.map.AddressMap;

public class ScheduleContext {

	private BucketScheduler bucketScheduler;
	private SimpleScheduler simpleScheduler;
	private AddressMap map;
	private Car car;
	private CommutePoint departure, arrival;
	private Commute scheduledCommute;
	
	/**
	 * Constructs a scheduler for the specified criteria.
	 * @param map map to use for scheduling
	 */
	public ScheduleContext(AddressMap map, Car car, CommutePoint departure, CommutePoint arrival) {
		this.map = map;
		this.car = car;
		this.departure = departure;
		this.arrival = arrival;
	}
	
	public void createSchedule() {
		if(true)
			scheduledCommute = simpleScheduler.schedule(map, car, departure, arrival);
		else if(true)
			scheduledCommute = bucketScheduler.schedule(map, car, departure, arrival);
	}
	
	public Commute getCommute() {
		return scheduledCommute;
	}
}
