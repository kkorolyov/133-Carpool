package dev.se133.project.schedule;

import java.util.*;

import dev.se133.project.car.Car;
import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.Address;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.Stop;
import dev.se133.project.commute.Time;
import dev.se133.project.commute.Time.Day;
import dev.se133.project.member.Member;

/**
 * Scheduler which does not support preferences.
 */
public class BasicCarpoolScheduler implements CarpoolScheduler {
	Time 	start,
				end;
	Address destination;
	Map<Time, List<Member>> members = new HashMap<>();
	int totalMembers;
	SchedulingPreference preferences;
	
	/**
	 * Constructs a new carpool scheduler for the specified properties.
	 * @param members all members to schedule
	 * @param start start point of schedule range
	 * @param end end point of schedule ranges
	 * @param preferences optional additional scheduling preferences
	 */
	public BasicCarpoolScheduler(Member[] members, Time start, Time end, Address destination, SchedulingPreference preferences) {
		setTimes(start, end);
		this.destination = destination;
		setMembers(members);
		this.preferences = preferences;
	}
	
	@Override
	public CarpoolSchedule schedule() {
		CarpoolSchedule schedule = new CarpoolSchedule();
		
		Time[] sortedTimes = getSortedTimes();
		
		for (Time time : sortedTimes) {
			List<Member> currentMembers = members.get(time);
			Car currentCar = null;
			Member currentDriver = null;
			
			if ((currentDriver = getDriver(currentMembers)) != null) {	// Current list has at least 1 driver
				currentCar = new Car(currentDriver.getRegisteredVehicles().getLargestVehicle().getCapacity(), currentDriver);
				
				currentMembers.remove(currentDriver);
			}
			Set<Member> currentPassengers = extractPassengers(currentMembers);
		}
		
		return schedule;
	}
	private static Member getDriver(List<Member> memberList) {		
		for (Member member : memberList) {
			if (member.isDriver())
				return member;
		}
		return null;
	}
	private static Set<Member> extractPassengers(List<Member> memberList) {
		Set<Member> passengers = new HashSet<>();
		
		for (Member member : memberList) {
			if (!member.isDriver())
				passengers.add(member);
		}
		
		return passengers;
	}
	private static void scheduleCarpool(CarpoolSchedule schedule, Car newCar, Day day) {
		Commute newCommute = buildCommute(newCar, day);
		Carpool newCarpool = new Carpool(newCommute, newCar);
		
		schedule.add(newCarpool);
	}
	private static Commute buildCommute(Car car, Day day) {
		Commute commute = new Commute();
		
		commute.addStop(earliestDeparture(car, day));
		commute.addStop(earliestArrival(car, day));
		
		return commute;
	}
	
	Time[] getSortedTimes() {
		Time[] toReturn = new Time[members.size()];
		
		int counter = 0;
		for (Time timeKey : members.keySet())
			toReturn[counter++] = timeKey;
		
		Arrays.sort(toReturn);
		
		return toReturn;
	}
	
	private void setTimes(Time newStart, Time newEnd) {
		this.start = newStart;
		this.end = newEnd;
	}
	private void setMembers(Member[] newMembers) {
		this.totalMembers = newMembers.length;
		
		for (Member member : newMembers) {
			Set<Stop> stopsInRange = member.getCommuteTimes().getStops(start, end, destination);
			
			for (Stop stop : stopsInRange) {
				if (members.get(stop) == null)
					members.put(stop.getTime(), new LinkedList<>());
				
				members.get(stop.getTime()).add(member);
			}
		}
	}
}
