package dev.se133.carpool.commute;

import dev.se133.carpool.commute.exception.TimeOutOfBoundsException;
import dev.se133.carpool.member.ConcreteMember;
public class RideScheduleDriver{

	RideScheduleDriver() throws TimeOutOfBoundsException {
		//Create initial Carpool
		ConcreteMember m1 = new ConcreteMember(52, "Joe", new Address("23 First St."));
		ConcreteMember m2 = new ConcreteMember(34, "Jack", new Address("123 Fake St."));
		ConcreteMember m3 = new ConcreteMember(15, "Robert", new Address("52 2nd St."));
		ConcreteMember m4 = new ConcreteMember(10, "Wilson", new Address("100 5th Ave."));
		ConcreteMember[] passengers = {m2, m3};
		CommutePoint[] stops = new CommutePoint[20];
		stops[0] = new CommutePoint(m3.getAddress(), new Time(9, 45));
		Commute commute = new Commute(Day.MONDAY, new CommutePoint(m1.getAddress(), new Time(9, 30)), new CommutePoint(m2.getAddress(), new Time(11, 00)), stops);
		Carpool testPool = new Carpool(commute, m1, passengers);
		//Retreive Carpool information
		System.out.println("Day: " + testPool.getCommute().getDay());
		System.out.println("Departure: " + commute.getDeparture().getAddress() + " -- " + commute.getDeparture().getTime().getHour() + ":" + commute.getDeparture().getTime().getMinute());
		
		for(int i = 0; i < stops.length; i++){
			if(stops[i] != null)
			System.out.println("Stops: " + stops[0].getAddress() + " -- " + stops[0].getTime().getHour() + ":" + stops[0].getTime().getMinute());
		}
		
		System.out.println("Arrival: " + commute.getArrival().getAddress() + " -- " + commute.getArrival().getTime().getHour() + ":" + commute.getArrival().getTime().getMinute());
	
		//Update Information
		stops[1] = new CommutePoint(m4.getAddress(), new Time(10, 15));
		System.out.println("-------------------Adding a new stop---------------------");
		
		for(int i = 0; i < stops.length; i++){
			if(stops[i] != null)
			System.out.println("Stops: " + stops[i].getAddress() + " -- " + stops[i].getTime().getHour() + ":" + stops[i].getTime().getMinute());
		}
		
		System.out.println("-------------------Deleting a stop---------------------");
		stops[0] = null;
		for(int i = 0; i < stops.length; i++){
			if(stops[i] != null)
			System.out.println("Stops: " + stops[i].getAddress() + " -- " + stops[i].getTime().getHour() + ":" + stops[i].getTime().getMinute());
		}
	}
	
	public static void main(String args[]) throws TimeOutOfBoundsException {
		new RideScheduleDriver();
	}
}
