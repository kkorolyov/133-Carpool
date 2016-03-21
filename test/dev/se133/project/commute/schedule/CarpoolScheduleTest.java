package dev.se133.project.commute.schedule;

import java.util.HashSet;
import java.util.Set;

import dev.se133.project.commute.*;
import dev.se133.project.commute.Time.Day;
import dev.se133.project.commute.Time.Month;
import dev.se133.project.member.BasicMember;
import dev.se133.project.member.Member;
import dev.se133.project.schedule.SortedCommuteSchedule;

@SuppressWarnings("javadoc")
public class CarpoolScheduleTest {	// TODO Remake better
	private static Set<Carpool> carpools = new HashSet<>();
	
	public static void main(String[] args) throws FullCarException, NoDriverException {
		populateCarpools();
		listAll();
		System.out.println();
		change();
		System.out.println();
		listAll();
		System.out.println();
		add();
		System.out.println();
		listAll();
		System.out.println();
		delete();
		System.out.println();
		listAll();
		System.out.println();
	}
	
	private static void listAll() {
		System.out.println("================================");
		System.out.println("CURRENT CARPOOLS");
		System.out.println("================================");
		for (Carpool carpool : carpools) {
			System.out.println("--------------------------------");
			System.out.println("Driver: " + carpool.getCar().getDriver().getName());
			System.out.println("Passengers: ");
			for (Member member : carpool.getCar().getInhabitants())
				System.out.println("\t" + member.getName());
			
			Commute commute = carpool.getCommute();
			Stop point;
			System.out.println("Commute:");
			System.out.println("\tDay: " + commute.getStart().getTime().getDay());
			point = commute.getStart();
			System.out.println("\tDeparture: " + point.getAddress() + " -- " + String.valueOf(point.getTime().getHour()) + ":" + String.valueOf(point.getTime().getMinute()));
			point = commute.getEnd();
			System.out.println("\tArrival: " + point.getAddress() + " -- " + String.valueOf(point.getTime().getHour()) + ":" + String.valueOf(point.getTime().getMinute()));
			
			System.out.println("\tStops: ");
			for (Stop stop : commute.getStops())
				System.out.println("\t\t" + stop.getAddress() + " -- " + String.valueOf(stop.getTime().getHour()) + ":" + String.valueOf(stop.getTime().getMinute()));
		}
	}
	private static void add() {
		int year = 2016;
		Month month = Month.JANUARY;
		Day day = Day.FRIDAY;
		int second = 0;
		
		System.out.println("ADDING A CARPOOL");
		Stop departure = new Stop(new Time(year, month, day, 11, 45, second), new Address("Departure St.")),
				arrival = new Stop(new Time(year, month, day, 18,30, second), new Address("Arrival Rd."));
		Stop stop = new Stop(new Time(year, month, day, 13, 15, second), new Address("AnotherStop Blvd."));
				
		Commute commute = new Commute();
		commute.addStop(stop);
		
		Member m1 = new BasicMember(52, "Joe", new Address("23 First St."), null),
				m2 = new BasicMember(34, "Jack", new Address("123 Fake St."), null);
				
		carpools.add(new Carpool(commute, new Car()));
	}
	private static void change() {
		System.out.println("CHANGING A DRIVER");
		for (Carpool carpool : carpools) {
			carpool.setDriver(new BasicMember(14, "New Guy", new Address("New Address")));
			break;
		}
	}
	private static void delete() {
		System.out.println("DELETING A CARPOOL");
		Carpool toDelete = null;
		for (Carpool carpool : carpools) {
			toDelete = carpool;
			break;
		}
		carpools.remove(toDelete);
	}
	
	private static void populateCarpools() throws FullCarException, NoDriverException {
		Member m1 = new BasicMember(52, "Joe", new Address("23 First St.")),
				m2 = new BasicMember(34, "Jack", new Address("123 Fake St.")),
				m3 = new BasicMember(15, "Robert", new Address("52 2nd St.")),
				m4 = new BasicMember(10, "Wilson", new Address("100 5th Ave."));
		Car passengers = new Car();
		passengers.addPassenger(m2);
		passengers.addPassenger(m3);

		Stop departure = new Stop(new Address("Departure St."), new Time(12, 45)),
				arrival = new Stop(new Address("Arrival Rd."), new Time(16,30));
		Stop stop1 = new Stop(new Address("Stop1 Blvd."), new Time(14, 15)),
				stop2 = new Stop(new Address("Stop2 Ave."), new Time(14, 20)),
				stop3 = new Stop(new Address("Stop3 St."), new Time(14, 25));
		
		Commute commute1 = new Commute(Day.SATURDAY, departure, arrival, new Stop[]{stop1, stop2}),
				commute2 = new Commute(Day.THURSDAY, departure, arrival, new Stop[]{stop1, stop3});
		
		carpools.add(new Carpool(commute1, m1, passengers));
		carpools.add(new Carpool(commute2, m4, passengers));
	}
}
