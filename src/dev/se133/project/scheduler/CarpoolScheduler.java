package dev.se133.project.scheduler;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import dev.se133.project.car.Car;
import dev.se133.project.car.FullCarException;
import dev.se133.project.car.NoDriverException;
import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.*;
import dev.se133.project.commute.builder.CommuteBuilder;
import dev.se133.project.map.AddressMap;
import dev.se133.project.member.Member;
import dev.se133.project.member.factory.MemberPool;

/**
 * Assigns and schedules carpools.
 */
public class CarpoolScheduler {
	private Stop end;	// TODO Remove
	private MemberPool members;	// All members
	private AddressMap map;	// Map of all addresses
	private Set<Carpool> carpools = new TreeSet<>(new Comparator<Carpool>() {
		@Override
		public int compare(Carpool o1, Carpool o2) {
			return o1.getCommute().getStart().compareTo(o2.getCommute().getStart());	// Compares carpools by their commutes' start times
		}
	});
	
	public CarpoolScheduler(MemberPool members, AddressMap map, Stop end) {
		this.members = members;
		this.map = map;
		this.end = end;
	}
	
	public void schedule(CommuteBuilder commuteBuilder) throws FullCarException, NoDriverException {
		TreeSet<Member> drivers = members.getDrivers(),
				passengers = members.getPassengers();
		
		while (!passengers.isEmpty() && !drivers.isEmpty()) {
			Member currentDriver = drivers.first();	// Next earliest driver
			drivers.remove(currentDriver);
			
			Car currentCar = new Car();
			
			currentCar.addPassenger(currentDriver);
			currentCar.setDriver();
			
			while(!currentCar.isFull() && !passengers.isEmpty()) {	// Load max passengers
				Member currentPassenger = passengers.first();	// Next earliest passenger
				passengers.remove(currentPassenger);
				
				currentCar.addPassenger(currentPassenger);
			}
			Commute currentCommute = commuteBuilder.buildCommute(map, currentCar, currentDriver.getPreferredCommutes().getFirstCommute().getStart(), end);
			
			carpools.add(new Carpool(currentCommute, currentCar));
		}
	}
	
	public Set<Carpool> getAllCarpools() {
		return new HashSet<Carpool>(carpools);
	}
}
