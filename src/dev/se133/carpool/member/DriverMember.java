package dev.se133.carpool.member;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dev.se133.carpool.commute.Address;
import dev.se133.carpool.commute.schedule.ConcreteCommuteSchedule;
import dev.se133.carpool.member.property.vehicle.Make;
import dev.se133.carpool.member.property.vehicle.Vehicle;
import dev.se133.carpool.member.property.vehicle.exception.YearOutOfBoundsException;
import dev.se133.carpool.member.state.MemberState;
import dev.se133.carpool.member.state.MemberState.Driver;

public class DriverMember {
	private Set<Member> members = new HashSet<>();
	
	public static void main() throws YearOutOfBoundsException {
		new DriverMember();
	}
	private static final Make FORD = null;

	DriverMember() throws YearOutOfBoundsException {
		
		members.add(new ConcreteMember(1, "Bob1", new Address("123 N P St")));
		members.add(new ConcreteMember(2, "Bob2", new Address("456 S P St")));
		members.add(new ConcreteMember(3, "Bob3", new Address("789 W P St")));
		
		listAll();
		System.out.println();
		changeAll();
		System.out.println();
		listAll();
		System.out.println();
		newAddition();
		System.out.println();
		listAll();
		System.out.println();
		Destroy();
		System.out.println();
		listAll();
	}
	
	private void listAll() {
		System.out.println("CURRENT LIST");
		for(Member x : members) {
			System.out.println("ID: " + x.getId());
			System.out.println("Name: " + x.getName());
			System.out.println("Address: " + x.getAddress().toString());
			System.out.println("State: " + x.getState().getStateName());
		}
	}
	
	private void changeAll() {
		int i = 0;
		String[] names = {"Bobby1", "Bobby2", "Bobby3"};
		String[] addresses = {"5 E St", "6 E St", "7 E St"};
		System.out.println("Changing the names of each member");
		for(Member x : members) {
			x.setName(names[i]);
			i++;
		}
		i = 0;
		
		System.out.println("Changing the addresses of each member");
		for(Member x : members) {
			x.setAddress(new Address(addresses[i]));
			i++;
		}
		
		System.out.println("Changing the state of each member");
		for(Member x : members) {
			x.setState(new MemberState.Driver());
		}
	}
	
	private void newAddition() {
		System.out.println("Adding a new member");
		members.add(new ConcreteMember(4, "BobbyNew", new Address("101 The Hard Way")));
	}
	
	private void Destroy() {
		System.out.println("Destroying a member");
		Member toDel = null;
		for(Member x : members) {
			toDel = x;
			break;
		}
		members.remove(toDel);
	}
}