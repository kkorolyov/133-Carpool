package dev.se133.project.schedule;

import java.util.*;

import dev.se133.project.car.Car;
import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.Address;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.Stop;
import dev.se133.project.commute.Time;
import dev.se133.project.member.Member;

/**
 * Scheduler which does not support preferences.
 */
public class BasicCarpoolScheduler implements CarpoolScheduler {
	private static final int PRESET_TRAVEL_TIME = -5 * 60;
	
	private final Comparator<Car> loadingCarsComparator;
	private final Comparator<Member> destinationMembersComparator;
	
	Time 	start,
				end;
	Address destination;
	Map<Time, List<Member>> members = new HashMap<>();
	int totalMembers;
	SchedulingPreference preferences;
	
	{	// Initialize comparator
		loadingCarsComparator = buildLoadingCarsComparator();
		destinationMembersComparator = buildDestinationMembersComparator();
	}
	private Comparator<Car> buildLoadingCarsComparator() {
		return new Comparator<Car>() {
			@SuppressWarnings("synthetic-access")
			@Override
			public int compare(Car o1, Car o2) {
				Time 	o1destinationTime = destinationTime(o1),
							o2DestinationTime = destinationTime(o2);
				
				return o1destinationTime.compareTo(o2DestinationTime);
			}
		};
	}
	private Time destinationTime(Car car) {
		Time earliestDestinationTime = null;
		
		for (Member member : car.getInhabitants()) {
			Time currentDestinationTime = member.getCommuteTimes().getStops(start, end, destination).iterator().next().getTime();
			
			if (earliestDestinationTime == null || currentDestinationTime.compareTo(earliestDestinationTime) < 0)
				earliestDestinationTime = currentDestinationTime;
		}
		return earliestDestinationTime;
	}
	
	private Comparator<Member> buildDestinationMembersComparator() {
		return new Comparator<Member>() {
			@SuppressWarnings("synthetic-access")
			@Override
			public int compare(Member o1, Member o2) {
				Time 	o1DestinationTime = destinationTime(o1),
							o2DestinationTime = destinationTime(o2);
				
				return o2DestinationTime.compareTo(o1DestinationTime);
			}
		};		
	}
	private Time destinationTime(Member member) {
		return member.getCommuteTimes().getStops(start, end, destination).iterator().next().getTime();
	}
	
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
		
		Queue<Member> queuedMembers = new LinkedList<>();
		Queue<Car> loadingCars = new LinkedList<>();
		
		for (Time time : getSortedTimes()) {
			Car currentCar = null;
			
			for (Member member : members.get(time)) {
				if (currentCar == null) {
					if (!member.isDriver()) {	// Passenger
						queuedMembers.add(member);	// No car to put member in
					}
					else {	// Driver
						currentCar = new Car(member);
					}
				}
				else { // Current car not full
					if (!member.isDriver()) {
						currentCar.addPassenger(member);
					}
				}
				if (currentCar != null && currentCar.isFull()) {
					schedule.add(buildCarpool(currentCar));	// Car full, schedule
					currentCar = null;
				}
			}
			if (currentCar != null) {
				if (!currentCar.isFull()) {
					loadingCars.add(currentCar);	// Car still has space
				}
				else {	// Full, schedule car
					schedule.add(buildCarpool(currentCar));
					currentCar = null;	// Probably not necessary
				}
			}
		}
		
		while (!loadingCars.isEmpty() && !queuedMembers.isEmpty()) {
			Car loadingCar = loadingCars.remove();
			
			while (!loadingCar.isFull() && !queuedMembers.isEmpty())
				loadingCar.addPassenger(queuedMembers.remove());
			
			schedule.add(buildCarpool(loadingCar));
		}
		while(!loadingCars.isEmpty())
			schedule.add(buildCarpool(loadingCars.remove()));
			
		return schedule;
	}
	private Carpool buildCarpool(Car car) {		
		return new Carpool(buildCommute(car), car);		
	}
	private Commute buildCommute(Car car) {
		Commute commute = new Commute();
		
		Set<Member> members = buildMemberSet(car);
		Iterator<Member> memberIterator = members.iterator();
		
		Time destinationTime = memberIterator.next().getCommuteTimes().getStops(start, end, destination).iterator().next().getTime();
		commute.addStop(new Stop(destinationTime, destination));
		
		Time stopTime = destinationTime;
		
		while (memberIterator.hasNext()) {
			stopTime = Time.timeAfter(stopTime, PRESET_TRAVEL_TIME);
			Address stopAddress = memberIterator.next().getAddress();
			
			commute.addStop(new Stop(stopTime, stopAddress));
		}
		return commute;
	}
	private Set<Member> buildMemberSet(Car car) {
		Set<Member> memberSet = new TreeSet<>(destinationMembersComparator);
		
		for (Member member : car.getInhabitants())
			memberSet.add(member);
		
		return memberSet;
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
					members.put(stop.getTime(), new LinkedList<Member>());
				
				members.get(stop.getTime()).add(member);
			}
		}
	}
}
