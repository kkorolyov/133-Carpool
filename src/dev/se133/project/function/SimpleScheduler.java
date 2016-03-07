package dev.se133.project.function;

import java.util.HashSet;
import java.util.Set;

import dev.se133.project.commute.*;
import dev.se133.project.map.AddressMap;
import dev.se133.project.member.Member;

public class SimpleScheduler {
	private AddressMap map;
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
	
	public void schedule() {	// TODO Issue here
		scheduledCommute = new CommuteBuilder() {
			@SuppressWarnings("synthetic-access")
			@Override
			public Commute buildCommute(Car car, CommutePoint departure, CommutePoint arrival) {
				Commute toReturn = new Commute(departure.getDay());
				
				try {
					toReturn.addStop(departure);
					toReturn.addStop(arrival);
					
					Set<Member> copySet = new HashSet<>(car.getInhabitants());
					
					CommutePoint lastStop = departure;
					while (!copySet.isEmpty()) {
						double minDistance = -1, currentDistance = -1;
						Member minMember = null;
						for (Member member : copySet) {
							if ((currentDistance = map.getDistance(member.getAddress(), lastStop.getAddress())) < minDistance || minDistance < 0) {
								minDistance = currentDistance;
								minMember = member;
							}
						}
						lastStop = new CommutePoint(minMember.getAddress(), lastStop.getDay(), new Time((int) (lastStop.getTime().getTotalMinutes() + minDistance + 1)));
						toReturn.addStop(lastStop);
						
						copySet.remove(minMember);
					}
				} catch (TimeOutOfBoundsException e) {
					e.printStackTrace();
				}
				return toReturn;
			}
		}.buildCommute(car, departure, arrival);
	}
	
	public Commute getCommute() {
		return scheduledCommute;
	}
}
