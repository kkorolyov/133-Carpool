package dev.se133.project.scheduler;

import dev.se133.project.commute.Car;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.Stop;
import dev.se133.project.commute.NoDriverException;
import dev.se133.project.map.AddressMap;

public interface SchedulingStrategy {

	public void createSchedule(AddressMap map, Car car, Stop departure, Stop arrival);
	
	public Commute schedule(AddressMap map, Car car, Stop departure, Stop arrival) throws NoDriverException;
}
