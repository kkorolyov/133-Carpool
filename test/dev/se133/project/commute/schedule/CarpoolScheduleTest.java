package dev.se133.project.commute.schedule;

import java.util.HashSet;
import java.util.Set;

import dev.se133.project.commute.*;
import dev.se133.project.member.BasicMember;
import dev.se133.project.member.Member;

@SuppressWarnings("javadoc")
public class CarpoolScheduleTest {
	private static Set<Carpool> carpools = new HashSet<>();
	
	public static void main(String[] args) throws TimeOutOfBoundsException, FullCarException, NoDriverException {
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
			CommutePoint point;
			System.out.println("Commute:");
			System.out.println("\tDay: " + commute.getDay());
			point = commute.getStart();
			System.out.println("\tDeparture: " + point.getAddress() + " -- " + String.valueOf(point.getTime().getHour()) + ":" + String.valueOf(point.getTime().getMinute()));
			point = commute.getEnd();
			System.out.println("\tArrival: " + point.getAddress() + " -- " + String.valueOf(point.getTime().getHour()) + ":" + String.valueOf(point.getTime().getMinute()));
			
			System.out.println("\tStops: ");
			for (CommutePoint stop : commute.getStops())
				System.out.println("\t\t" + stop.getAddress() + " -- " + String.valueOf(stop.getTime().getHour()) + ":" + String.valueOf(stop.getTime().getMinute()));
		}
	}
	private static void add() throws TimeOutOfBoundsException {
		System.out.println("ADDING A CARPOOL");
		CommutePoint departure = new CommutePoint(new Address("Departure St."), new Time(11, 45)),
				arrival = new CommutePoint(new Address("Arrival Rd."), new Time(18,30));
		CommutePoint stop = new CommutePoint(new Address("AnotherStop Blvd."), new Time(13, 15));
				
		Commute commute = new Commute(Day.FRIDAY, departure, arrival, new CommutePoint[]{stop});
		
		Member m1 = new BasicMember(52, "Joe", new Address("23 First St.")),
				m2 = new BasicMember(34, "Jack", new Address("123 Fake St."));
				
		carpools.add(new Carpool(commute, m1, new Car()));
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
	
	private static void populateCarpools() throws TimeOutOfBoundsException, FullCarException, NoDriverException {
		Member m1 = new BasicMember(52, "Joe", new Address("23 First St.")),
				m2 = new BasicMember(34, "Jack", new Address("123 Fake St.")),
				m3 = new BasicMember(15, "Robert", new Address("52 2nd St.")),
				m4 = new BasicMember(10, "Wilson", new Address("100 5th Ave."));
		Car passengers = new Car();
		passengers.addPassenger(m2);
		passengers.addPassenger(m3);

		CommutePoint departure = new CommutePoint(new Address("Departure St."), new Time(12, 45)),
				arrival = new CommutePoint(new Address("Arrival Rd."), new Time(16,30));
		CommutePoint stop1 = new CommutePoint(new Address("Stop1 Blvd."), new Time(14, 15)),
				stop2 = new CommutePoint(new Address("Stop2 Ave."), new Time(14, 20)),
				stop3 = new CommutePoint(new Address("Stop3 St."), new Time(14, 25));
		
		Commute commute1 = new Commute(Day.SATURDAY, departure, arrival, new CommutePoint[]{stop1, stop2}),
				commute2 = new Commute(Day.THURSDAY, departure, arrival, new CommutePoint[]{stop1, stop3});
		
		carpools.add(new Carpool(commute1, m1, passengers));
		carpools.add(new Carpool(commute2, m4, passengers));
	}
}
