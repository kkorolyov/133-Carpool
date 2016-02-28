package dev.se133.carpool.member;

import java.util.ArrayList;
import java.util.Scanner;

import dev.se133.carpool.commute.Address;
import dev.se133.carpool.member.property.vehicle.Make;
import dev.se133.carpool.member.property.vehicle.Vehicle;
import dev.se133.carpool.member.property.vehicle.exception.YearOutOfBoundsException;

public class MemberTester {

	public static void main(String args[]) throws YearOutOfBoundsException {
		//Must show status, address, vehicles, sharing ride preference)
		//Still need ride pref and status
		Scanner in = new Scanner(System.in);
		String a;
		String b;
		String model;
		int year;
		String vin;
		Make make = Make.TOYOTA;
		ArrayList<ConcreteMember> MemberList = new ArrayList<ConcreteMember>();
		
		System.out.println("What is your name?");
		a = in.nextLine();
		System.out.println("What is your address?");
		b = in.nextLine();
		ConcreteMember m1 = new ConcreteMember(a, new Address(b));
		MemberList.add(m1);
		

		System.out.println("Vehicle Model: ");
		model = in.nextLine();
		System.out.println("Vehicle Year: ");
		year = Integer.parseInt(in.nextLine());
		System.out.println("VIN: ");
		vin = in.nextLine();
		Vehicle v1 = new Vehicle( make, model, year, vin);
		
		in.close();
		
		System.out.println("Member Name: " + MemberList.get(0).getName());
		System.out.println("Member Address: " + MemberList.get(0).getAddress());
		System.out.println("Vehicle Make: " + v1.getMake());
		System.out.println("Vehicle Model: " + v1.getModel());
		System.out.println("Vehicle Year: " + v1.getYear());
		System.out.println("Vehicle VIN: " + v1.getVin());

	}
}
