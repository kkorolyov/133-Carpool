package dev.se133.carpool.member;

import java.util.ArrayList;
import java.util.Scanner;

import dev.se133.carpool.commute.Address;

public class MemberTester {

	public static void main(String args[]) {
	
		Scanner in = new Scanner(System.in);
		String a;
		String b;
		ArrayList<ConcreteMember> MemberList = new ArrayList<ConcreteMember>();
		
		System.out.println("What is your name?");
		a = in.nextLine();
		System.out.println("What is your address?");
		b = in.nextLine();
		ConcreteMember m1 = new ConcreteMember(a, new Address(b));
		MemberList.add(m1);
		in.close();
		System.out.println("Member Name: " + MemberList.get(0).getName());
		System.out.println("Member Address: " + MemberList.get(0).getAddress());

	}
}
