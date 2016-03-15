package dev.se133.project.function;

import dev.se133.project.commute.Address;
import dev.se133.project.commute.Car;
import dev.se133.project.commute.FullCarException;
import dev.se133.project.commute.NoDriverException;
import dev.se133.project.member.BasicMember;
import dev.se133.project.member.MemberState;

public class NotifyTester {

	public static void main(String[] args) throws FullCarException, NoDriverException {
	
		BasicMember a = new BasicMember(1,"Matt", new Address("246 North 6th Street"));
		a.setState(new MemberState.Driver());
		BasicMember b = new BasicMember(2,"Mark", new Address("250 North 6th Street"));
		BasicMember c = new BasicMember(3,"Ryan", new Address("255 North 6th Street"));
		BasicMember d = new BasicMember(4,"Nnamdi", new Address("278 North 6th Street"));
		BasicMember e = new BasicMember(5,"Kirill", new Address("290 North 6th Street"));
		
		Car car = new Car();
		Car carb= new Car();
		
		
		car.addPassenger(a);
		car.addPassenger(b);
		car.addPassenger(c);
		car.setDriver(a);
		car.addPassenger(d);
		car.addPassenger(e);
		car.removePassenger(d);
		car.addPassenger(d);
		//car.addPassenger(new BasicMember(6,"Lee", new Address("123 Story Lane")));
		carb.addPassenger(a);
		carb.addPassenger(b);
		
		

	}

}
