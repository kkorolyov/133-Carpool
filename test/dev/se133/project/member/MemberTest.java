package dev.se133.project.member;

import java.util.HashSet;
import java.util.Set;

import dev.se133.project.commute.Address;
import dev.se133.project.member.BasicMember;
import dev.se133.project.member.Member;
import dev.se133.project.member.MemberState;

@SuppressWarnings("javadoc")
public class MemberTest {
	private Set<Member> members = new HashSet<>();
	
	public static void main(String[] args) throws YearOutOfBoundsException {
		new MemberTest();
	}

	MemberTest() throws YearOutOfBoundsException {
		
		members.add(new BasicMember(1, "Bob1", new Address("123 N P St")));
		members.add(new BasicMember(2, "Bob2", new Address("456 S P St")));
		members.add(new BasicMember(3, "Bob3", new Address("789 W P St")));
		
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
			
			for (Vehicle vehicle : x.getGarage().getVehicles()) {
				System.out.println("\tMake: " + vehicle.getMake());
				System.out.println("\tModel: " + vehicle.getModel());
				System.out.println("\tYear: " + vehicle.getYear());
				System.out.println("\tVIN: " + vehicle.getVin());
			}
		}
	}
	
	private void changeAll() throws YearOutOfBoundsException {
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
		
		System.out.println("Adding vehicles to each member");
		for (Member x : members)
			x.getGarage().addVehicle(x.getName() + "'s car", new Vehicle(Vehicle.Make.HONDA, "NOTAMODEL", 2005, String.valueOf(x.hashCode())));
	}
	
	private void newAddition() {
		System.out.println("Adding a new member");
		members.add(new BasicMember(4, "BobbyNew", new Address("101 The Hard Way")));
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