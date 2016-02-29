package dev.se133.carpool.member;

import java.util.Arrays;

import dev.se133.carpool.commute.Address;
import dev.se133.carpool.commute.schedule.ConcreteCommuteSchedule;
import dev.se133.carpool.member.property.vehicle.Make;
import dev.se133.carpool.member.property.vehicle.Vehicle;
import dev.se133.carpool.member.property.vehicle.exception.YearOutOfBoundsException;

public class DriverMember {
	private static final Make FORD = null;

	DriverMember() throws YearOutOfBoundsException {
		System.out.println("Creating a new member named Bob");
		
		//constructor
		ConcreteMember bob = new ConcreteMember("Bob", new Address("123 N P St"), new ConcreteCommuteSchedule());
		
		//get original name
		System.out.println(bob.getName());
		
		//set new name
		bob.setName("Bobby");
		
		//get new name
		System.out.println(bob.getName());
		
		//get original address
		System.out.println(bob.getAddress());
		
		//set new address
		bob.setAddress(new Address("456 S Q St"));
		
		//get new address
		System.out.println(bob.getAddress());
		
		//add vehicle
		bob.addVehicle("Fiesta" ,new Vehicle(FORD, "Fiesta", 2000, "123VIN"));
		
		//remove vehicle
		bob.removeVehicle("Fiesta");
		
		//get vehicle
		Arrays.toString(bob.getVehicles());
		
		/*@Override
		public CommuteSchedule getPreferredCommutes() {
			return preferredCommutes;
		}*/
		
		//get user's original state
		System.out.println(bob.getState());

		//set user's new state
		//bob.setState(State state);
			
		//get user's new state
		//System.out.println(bob.getState())
	}
}