package dev.se133.project.scheduler;

import java.util.Set;

import dev.se133.project.car.Car;
import dev.se133.project.car.NoDriverException;
import dev.se133.project.commute.*;
import dev.se133.project.commute.builder.CommuteBuilder;
import dev.se133.project.map.AddressMap;
import dev.se133.project.member.Member;
import dev.se133.project.member.MemberManager;

public class SimpleScheduler implements SchedulingStrategy {
	Commute scheduledCommute;
	
	@Override
	public void createSchedule(final AddressMap map, Car car, Stop departure, Stop arrival) throws NoDriverException {
		schedule(map, car, departure, arrival, new CommuteBuilder() {
			@Override
			public Commute buildCommute(AddressMap map, Car car, Stop departure, Stop arrival) {
				Commute commute = new Commute();
				
				try {
					commute.addStop(departure);	// Add departure point to commute
					commute.addStop(arrival);	// Add arrival point to commute
					
					Set<Member> allInhabitants = car.getInhabitants();	// Returned set is a copy, ok to mutate
					
					if (car.getDriver().getAddress().equals(departure.getAddress()))	// Driver departing from own address, no need to add
						allInhabitants.remove(car.getDriver());
					
					Stop lastStop = departure;	// Start routing from 1st point = departure
					
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
		});
	}
	
	/*public void scheduleByDriver(final AddressMap map, Car car, CommutePoint departure, CommutePoint arrival) {
		schedule(map, car, departure, arrival, new CommuteBuilder() {
			public Commute buildCommute(Car car, CommutePoint departure, CommutePoint arrival) {
				Commute commute = new Commute(departure.getDay());
				
				try {
					commute.addStop(departure);	// Add departure point to commute
					commute.addStop(arrival);	// Add arrival point to commute
					
					Set<Member> allInhabitants = car.getInhabitants();	// Returned set is a copy, ok to mutate
					
					if (car.getDriver().getAddress().equals(departure.getAddress()))	// Driver departing from own address, no need to add
						allInhabitants.remove(car.getDriver());
					
					CommutePoint lastStop = departure;	// Start routing from 1st point = departure
					
					int id;
					Scanner scan = new Scanner(System.in);
					while (!allInhabitants.isEmpty()) {
						System.out.println("Who would you like to pick up next?" + allInhabitants.iterator());
						id = scan.nextInt();
						double minDistance = Double.MAX_VALUE;	// No distance in commute should be greater than this
						Member picked = null;
						for (Member inhabitant : allInhabitants) {
							double currentDistance = map.getDistance(lastStop.getAddress(), inhabitant.getAddress());
							if (id == inhabitant.getId()) {
								
								picked = inhabitant;
							}
							if (currentDistance < minDistance) {
								minDistance = currentDistance;
							}
						}
						commute.addStop(lastStop = new CommutePoint(picked.getAddress(), lastStop.getDay(), new Time(lastStop.getTime().getTotalMinutes() + (int) minDistance + 1)));
						allInhabitants.remove(picked);
					}
					scan.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return commute;
			}
		});
	}*/
	
	/*private void scheduleDriver() {
		scheduleByDriver(map, car, departure, arrival);
	}*/
	
	
	
	private void schedule(AddressMap map, Car car, Stop departure, Stop arrival, CommuteBuilder algorithm) throws NoDriverException {
		// TODO Change to add to carpool list
		scheduledCommute = algorithm.buildCommute(map, car, departure, arrival);
	}
	
	@Override
	public Commute schedule(AddressMap map, Car car, Stop departure, Stop arrival) throws NoDriverException {
		createSchedule(map, car, departure, arrival);
		return scheduledCommute;
	}
	
	public Commute getCommute() {
		return scheduledCommute;
	}
}
