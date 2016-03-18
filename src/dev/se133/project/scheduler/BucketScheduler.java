package dev.se133.project.scheduler;

import dev.se133.project.commute.Car;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.CommutePoint;
import dev.se133.project.commute.NoDriverException;
import dev.se133.project.commute.Time;
import dev.se133.project.map.AddressMap;
import dev.se133.project.member.Member;
import dev.se133.project.member.MemberManager;
import dev.se133.project.router.CommuteBuilder;

public class BucketScheduler implements SchedulingStrategy {

	Commute scheduledCommute;
	
	@Override
	public void createSchedule(final AddressMap map, Car car, CommutePoint departure, CommutePoint arrival) throws NoDriverException {
		schedule(map, car, departure, arrival, new CommuteBuilder() {
			public Commute buildCommute(AddressMap map, Car car, CommutePoint departure, CommutePoint arrival) {
				Commute commute = new Commute();
				try {
				commute.addStop(departure);	// Add departure point to commute
				commute.addStop(arrival);	// Add arrival point to commute
				
				MemberManager manager = MemberManager.getManager();
				Member driver = car.getDriver();
				double driverMaxTime = driver.getMaxTime(), driverMaxDistance = driver.getMaxDistance();
				
				CommutePoint lastStop = departure;	// Start routing from 1st point = departure
				
				if(!car.isFull()) {
					Member[] passengers = manager.getSameBucketPassengers(driver);
					for(Member passenger : passengers) {
						if(driverMaxTime == 0 || driverMaxDistance == 0)
							return commute;
						if(driverMaxTime - map.getTime(passenger.getAddress(), arrival.getAddress()) >= 0
								&& driverMaxDistance - map.getDistance(passenger.getAddress(), arrival.getAddress()) >= 0) {
							commute.addStop(lastStop = new CommutePoint(passenger.getAddress(), Time.timeAfter(lastStop.getTime(), (int) map.getDistance(passenger.getAddress(), lastStop.getAddress()) + 1)));
							driverMaxTime = map.getTime(passenger.getAddress(), arrival.getAddress()) + map.getTime(passenger.getAddress(), driver.getAddress());
							driverMaxDistance = map.getDistance(passenger.getAddress(), arrival.getAddress()) + map.getDistance(passenger.getAddress(), driver.getAddress());
						}
					}
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return commute;
			}
		});
	}
	
	private void schedule(AddressMap map, Car car, CommutePoint departure, CommutePoint arrival, CommuteBuilder algorithm) throws NoDriverException {
		// TODO Change to add to carpool list
		scheduledCommute = algorithm.buildCommute(map, car, departure, arrival);
	}
	
	public Commute schedule(AddressMap map, Car car, CommutePoint departure, CommutePoint arrival) throws NoDriverException {
		createSchedule(map, car, departure, arrival);
		return scheduledCommute;
	}

}
