package dev.se133.project.member;

import dev.se133.project.commute.Address;
import dev.se133.project.commute.Car;
import dev.se133.project.schedule.SortedCommuteSchedule;

public class NotifyTester {

	public static void main(String[] args) {
		
		Car a = new Car();
		Member b = new BasicMember(1, "Matt", new Address("468 South 6th Street") ,new SortedCommuteSchedule(),new MemberState.Driver());
		Member c = new BasicMember(2, "Mark", new Address("468 South 6th Street") ,new SortedCommuteSchedule());
		Member d = new BasicMember(3, "Kiril", new Address("468 South 6th Street") ,new SortedCommuteSchedule());
		Member e = new BasicMember(4, "Nnamdi", new Address("468 South 6th Street") ,new SortedCommuteSchedule());
		Member f = new BasicMember(5, "Ryan", new Address("468 South 6th Street") ,new SortedCommuteSchedule());
		
		a.addPassenger(b);
		a.setDriver(b);
		a.addPassenger(c);
		a.addPassenger(d);
		a.addPassenger(e);
		a.addPassenger(f);
		
		a.removePassenger(f);

	}

}
