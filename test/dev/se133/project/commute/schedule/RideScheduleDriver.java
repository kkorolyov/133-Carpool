package dev.se133.project.commute.schedule;

import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.*;
import dev.se133.project.member.BasicMember;
import dev.se133.project.member.car.Car;
import dev.se133.project.member.car.FullCarException;
import dev.se133.project.member.car.NoDriverException;
@SuppressWarnings("javadoc")
public class RideScheduleDriver{	// TODO Remake better

	RideScheduleDriver() throws FullCarException, NoDriverException {
		//Create initial Carpool
		BasicMember m1 = new BasicMember(52, "Joe", new Address("23 First St."));
		BasicMember m2 = new BasicMember(34, "Jack", new Address("123 Fake St."));
		BasicMember m3 = new BasicMember(15, "Robert", new Address("52 2nd St."));
		BasicMember m4 = new BasicMember(10, "Wilson", new Address("100 5th Ave."));
		Car passengers = new Car();
		passengers.addPassenger(m2);
		passengers.addPassenger(m3);
		Stop[] stops = new Stop[20];
		stops[0] = new Stop(m3.getAddress(), new Time(9, 45));
		Commute commute = new Commute(Day.MONDAY, new Stop(m1.getAddress(), new Time(9, 30)), new Stop(m2.getAddress(), new Time(11, 00)), stops);
		Carpool testPool = new Carpool(commute, m1, passengers);
		
		Stop[] stopList = commute.getStops();
		
		//Retreive Carpool information
		System.out.println("Day: " + testPool.getCommute().getDay());
		System.out.println("Departure: " + commute.getStart().getAddress() + " -- " + commute.getStart().getTime().getHour() + ":" + commute.getStart().getTime().getMinute());
		
		//System.out.println(stopList[1].getAddress());

		for(int i = 0; i < stopList.length; i++){
			if(stopList[i] != null)
			System.out.println("Stops: " + stopList[i].getAddress() + " -- " + stopList[i].getTime().getHour() + ":" + stopList[i].getTime().getMinute());
		}
		
		System.out.println("Arrival: " + commute.getEnd().getAddress() + " -- " + commute.getEnd().getTime().getHour() + ":" + commute.getEnd().getTime().getMinute());
	
		//Update Information
		commute.addStop(new Stop(m4.getAddress(), new Time(10,15)));
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
	
	public static void main(String args[]) throws FullCarException, NoDriverException {
		new RideScheduleDriver();
	}
}
