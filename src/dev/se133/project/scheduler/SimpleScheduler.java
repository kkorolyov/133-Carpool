package dev.se133.project.scheduler;

import java.util.Set;

import dev.se133.project.commute.*;
import dev.se133.project.map.AddressMap;
import dev.se133.project.member.Member;
import dev.se133.project.member.MemberManager;
import dev.se133.project.router.CommuteBuilder;

public class SimpleScheduler {
	private AddressMap map;
	// TODO Remove these
	private Car car;
	private CommutePoint departure, arrival;
	private Commute scheduledCommute;
	
	/**
	 * Constructs a scheduler for the specified criteria.
	 * @param map map to use for scheduling
	 */
	public SimpleScheduler(AddressMap map, Car car, CommutePoint departure, CommutePoint arrival) {
		this.map = map;
		this.car = car;
		this.departure = departure;
		this.arrival = arrival;
	}
	
	public void scheduleByDistance(final AddressMap map, Car car, CommutePoint departure, CommutePoint arrival) throws NoDriverException {
		schedule(map, car, departure, arrival, new CommuteBuilder() {
			@Override
			public Commute buildCommute(AddressMap map, Car car, CommutePoint departure, CommutePoint arrival) {
				Commute commute = new Commute();
				
				try {
					commute.addStop(departure);	// Add departure point to commute
					commute.addStop(arrival);	// Add arrival point to commute
					
					Set<Member> allInhabitants = car.getInhabitants();	// Returned set is a copy, ok to mutate
					
					if (car.getDriver().getAddress().equals(departure.getAddress()))	// Driver departing from own address, no need to add
						allInhabitants.remove(car.getDriver());
					
					CommutePoint lastStop = departure;	// Start routing from 1st point = departure
					
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
						commute.addStop(lastStop = new CommutePoint(minMember.getAddress(), Time.timeAfter(lastStop.getTime(), (int) minDistance + 1)));
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
	
	public void scheduleUsingBucketLists(final AddressMap map, Car car, CommutePoint departure, CommutePoint arrival) throws NoDriverException {
		schedule(map, car, departure, arrival, new CommuteBuilder() {
			public Commute buildCommute(AddressMap map, Car car, CommutePoint departure, CommutePoint arrival) {
				Commute commute = new Commute();
				try {
				commute.addStop(departure);	// Add departure point to commute
				commute.addStop(arrival);	// Add arrival point to commute
				
				MemberManager manager = MemberManager.getManager();
				Member driver = car.getDriver();
				
				CommutePoint lastStop = departure;	// Start routing from 1st point = departure
				
				if(!car.isFull()) {
					Member[] passengers = manager.getSameBucketPassengers(driver);
					for(Member passenger : passengers) {
						if(driver.getMaxTime() - map.getTime(passenger.getAddress(), arrival.getAddress()) >= 0
								&& driver.getMaxDistance() - map.getDistance(passenger.getAddress(), arrival.getAddress()) >= 0) {
							commute.addStop(lastStop = new CommutePoint(passenger.getAddress(), Time.timeAfter(lastStop.getTime(), (int) map.getDistance(passenger.getAddress(), lastStop.getAddress()) + 1)));
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
	
	public void schedule() throws NoDriverException {
		scheduleByDistance(map, car, departure, arrival);
	}
	
	public Commute getCommute() {
		return scheduledCommute;
	}
}
