package dev.se133.project.commute.schedule;

import dev.se133.project.entity.Address;
import dev.se133.project.entity.Car;
import dev.se133.project.entity.Carpool;
import dev.se133.project.entity.Day;
import dev.se133.project.entity.Time;
import dev.se133.project.entity.commute.*;
import dev.se133.project.entity.exception.FullCarException;
import dev.se133.project.entity.exception.TimeOutOfBoundsException;
import dev.se133.project.entity.member.BasicMember;
@SuppressWarnings("javadoc")
public class RideScheduleDriver{

	RideScheduleDriver() throws TimeOutOfBoundsException, FullCarException {
		//Create initial Carpool
		BasicMember m1 = new BasicMember(52, "Joe", new Address("23 First St."));
		BasicMember m2 = new BasicMember(34, "Jack", new Address("123 Fake St."));
		BasicMember m3 = new BasicMember(15, "Robert", new Address("52 2nd St."));
		BasicMember m4 = new BasicMember(10, "Wilson", new Address("100 5th Ave."));
		Car passengers = new Car();
		passengers.addPassenger(m2);
		passengers.addPassenger(m3);
		CommutePoint[] stops = new CommutePoint[20];
		stops[0] = new CommutePoint(m3.getAddress(), new Time(9, 45));
		Commute commute = new Commute(Day.MONDAY, new CommutePoint(m1.getAddress(), new Time(9, 30)), new CommutePoint(m2.getAddress(), new Time(11, 00)), stops);
		Carpool testPool = new Carpool(commute, m1, passengers);
		
		CommutePoint[] stopList = commute.getStops();
		
		//Retreive Carpool information
		System.out.println("Day: " + testPool.getCommute().getDay());
		System.out.println("Departure: " + commute.getDeparture().getAddress() + " -- " + commute.getDeparture().getTime().getHour() + ":" + commute.getDeparture().getTime().getMinute());
		
		//System.out.println(stopList[1].getAddress());

		for(int i = 0; i < stopList.length; i++){
			if(stopList[i] != null)
			System.out.println("Stops: " + stopList[i].getAddress() + " -- " + stopList[i].getTime().getHour() + ":" + stopList[i].getTime().getMinute());
		}
		
		System.out.println("Arrival: " + commute.getArrival().getAddress() + " -- " + commute.getArrival().getTime().getHour() + ":" + commute.getArrival().getTime().getMinute());
	
		//Update Information
		commute.addStop(new CommutePoint(m4.getAddress(), new Time(10,15)));
		stopList = commute.getStops();
		System.out.println("-------------------Adding a new stop---------------------");
		
		for(int i = 0; i < stopList.length; i++){
			if(stopList[i] != null)
			System.out.println("Stops: " + stopList[i].getAddress() + " -- " + stopList[i].getTime().getHour() + ":" + stopList[i].getTime().getMinute());
		}
		
		System.out.println("-------------------Deleting a stop---------------------");
		commute.removeStop(stopList[1]);
		stopList = commute.getStops();
		for(int i = 0; i < stopList.length; i++){
			if(stopList[i] != null)
			System.out.println("Stops: " + stopList[i].getAddress() + " -- " + stopList[i].getTime().getHour() + ":" + stopList[i].getTime().getMinute());
		}
	}
	
	public static void main(String args[]) throws TimeOutOfBoundsException, FullCarException {
		new RideScheduleDriver();
	}
}