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
		
		
		
		return schedule;
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
