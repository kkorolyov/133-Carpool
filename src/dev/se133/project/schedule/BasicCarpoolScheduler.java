package dev.se133.project.schedule;

import java.util.*;

import dev.se133.project.car.Car;
import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.Stop;
import dev.se133.project.commute.Time;
import dev.se133.project.commute.Time.Day;
import dev.se133.project.member.Member;
import dev.se133.project.member.preferences.CommutePreference;

/**
 * Scheduler which does not support preferences.
 */
public class BasicCarpoolScheduler implements CarpoolScheduler {
	Time 	start,
				end;
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
	public BasicCarpoolScheduler(Member[] members, Time start, Time end, SchedulingPreference preferences) {
		setTimes(start, end);
		setMembers(members);
		this.preferences = preferences;
	}
	
	@Override
	public CarpoolSchedule schedule() {
		CarpoolSchedule schedule = new CarpoolSchedule();
		
		Set<Member> scheduledMembers = new HashSet<>();
		Time[] sortedTimes = getSortedTimes();
		
		boolean uselessIteration = false;	// Avoids endless loop when not possible to schedule everyone
		while(scheduledMembers.size() < totalMembers && !uselessIteration) {
			uselessIteration = true;	// Assume true
			
			Car currentCar = null;
			Day currentDay = null;
			
			for (Time time : sortedTimes) {
				List<Member> currentMembers = members.get(time);
				
				for (Member currentMember : currentMembers) {
					if (!scheduledMembers.contains(currentMember)) {	// Member not yet scheduled
						if (currentCar != null && currentCar.isFull()) {
							scheduleCarpool(schedule, currentCar, currentDay);
							currentCar = null;
						}
						if (currentCar == null) {
							if (currentMember.isDriver()) {
								currentCar = new Car(currentMember.getRegisteredVehicles().getLargestVehicle().getCapacity(), currentMember);
								currentDay = currentMember.getCommuteTimes().getEarliest().getDay();
								
								scheduledMembers.add(currentMember);
								uselessIteration = false;	// Someone added
							}
						}
						else if (!currentCar.isFull() && currentMember.getCommuteTimes().getPreference(currentDay) != null) {
							currentCar.addPassenger(currentMember);
							
							scheduledMembers.add(currentMember);
							uselessIteration = false;
						}
					}
				}
			}
			if (currentCar != null) {
				scheduleCarpool(schedule, currentCar, currentDay);
			}
		}
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
	private static Stop earliestDeparture(Car car, Day day) {
		Stop earliestDeparture = null;
		Time earliestDepartureTime = null;
		
		for (Member carMember : car.getInhabitants()) {
			Time currentMemberEarliestDeparture = carMember.getCommuteTimes().getPreference(day).getTime(CommutePreference.TO_DESTINATION);
			
			if (currentMemberEarliestDeparture != null && (earliestDepartureTime == null || currentMemberEarliestDeparture.compareTo(earliestDepartureTime) < 0)) {
				earliestDepartureTime = currentMemberEarliestDeparture;
				
				earliestDeparture = new Stop(earliestDepartureTime, carMember.getAddress());
			}
		}
		return earliestDeparture;
	}
	private static Stop earliestArrival(Car car, Day day) {
		Stop earliestArrival = null;
		Time earliestArrivalTime = null;
		
		for (Member carMember : car.getInhabitants()) {
			Time currentMemberEarliestArrival = carMember.getCommuteTimes().getPreference(day).getTime(CommutePreference.AT_DESTINATION);
			
			if (currentMemberEarliestArrival != null && (earliestArrivalTime == null || currentMemberEarliestArrival.compareTo(earliestArrivalTime) < 0)) {
				earliestArrivalTime = currentMemberEarliestArrival;
				
				earliestArrival = new Stop(earliestArrivalTime, carMember.getAddress());
			}
		}
		return earliestArrival;
	}
	
	Time[] getSortedTimes() {
		Time[] sortedTimeKeys = new Time[members.size()];
		
		int counter = 0;
		for (Time timeKey : members.keySet())
			sortedTimeKeys[counter++] = timeKey;
		
		Arrays.sort(sortedTimeKeys);
		
		return sortedTimeKeys;
	}
	
	private void setTimes(Time newStart, Time newEnd) {
		this.start = newStart;
		this.end = newEnd;
	}
	private void setMembers(Member[] newMembers) {
		this.totalMembers = newMembers.length;
		
		for (Member member : newMembers) {
			Time currentStartTime = member.getCommuteTimes().getEarliest();
			
			if (currentStartTime.compareTo(start) >= 0) {	// Only load members within the time range
				if (members.get(currentStartTime) == null)
					members.put(currentStartTime, new LinkedList<>());
			
				members.get(currentStartTime).add(member);
			}
		}
	}
}
