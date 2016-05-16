package allUCs;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

import dev.se133.project.commute.Address;
import dev.se133.project.commute.Stop;
import dev.se133.project.commute.Time;
import dev.se133.project.commute.Time.Day;
import dev.se133.project.commute.Time.Month;
import dev.se133.project.member.Member;
import dev.se133.project.member.garage.Garage;
import dev.se133.project.member.garage.Vehicle;
import dev.se133.project.member.garage.Vehicle.Make;
import dev.se133.project.member.garage.YearOutOfBoundsException;
import dev.se133.project.member.preferences.CommuteSchedule;
import dev.se133.project.member.wallet.Wallet;
import dev.se133.project.schedule.ScheduleFactory;
import dev.se133.project.schedule.SchedulingPreference;

public class mainProgram {
	private final Address sjsu = new Address("SJSU");
	private ArrayList<Member> members = new ArrayList<Member>();
	private boolean loggedIn = false;
	private Time mondayTime = new Time(2016, Month.MAY, Day.MONDAY, 8, 0, 0, 9);
	private Time tuesdayTime = Time.timeAfter(mondayTime, 86400);
	private Time wednesdayTime = Time.timeAfter(tuesdayTime, 86400);
	private Time thursdayTime = Time.timeAfter(wednesdayTime, 86400);
	private Time fridayTime = Time.timeAfter(thursdayTime, 86400);
	private Time[] weekdays = new Time[5];
	private Scanner userInput = new Scanner(System.in);;

	public static void main(String[] args) throws YearOutOfBoundsException {
		new mainProgram();
	}
	public mainProgram() throws YearOutOfBoundsException {
		String choice, memberFields[] = new String[20];
		String userName;
		weekdays[0] = mondayTime;
		weekdays[1] = tuesdayTime;
		weekdays[2] = wednesdayTime;
		weekdays[3] = thursdayTime;
		weekdays[4] = fridayTime;
		
		while(true) {
			System.out.println("create a member: 1 \nview schedules: 2 \ncreate schedule: 3\nlog in: 4 \nquit: 0");
			
			choice = userInput.next();
			if(choice.equals("1")) {
				System.out.println("Enter name");
				memberFields[0] = userInput.next();
				System.out.println("Driver? true/false");
				memberFields[1] = userInput.next();
				System.out.println("Enter address");
				memberFields[2] = userInput.next();
				createMember(memberFields);
			}
				
			if(choice.equals("2")) {
				viewSchedules();
			}
			
			if(choice.equals("3")) {
				createSchedule();
			}
					
			if(choice.equals("4")) {
				System.out.println("Enter member name");
				userName = userInput.next();
				login(userName);
			}
			
			if(choice.equals("0")) {
				System.exit(1);;
			}
		}
		
	}

	
	public void createMember(String[] memberFields) {
		if(memberFields[1].equals("true"))
			members.add(new Member(members.size(), memberFields[0], true, new Address(memberFields[2]), new Wallet(), new Garage(), new CommuteSchedule()));
		else
			members.add(new Member(members.size(), memberFields[0], false, new Address(memberFields[2]), new Wallet(), new Garage(), new CommuteSchedule()));
	}
	
	public void viewSchedules() {
		
	}
	
	public void login(String userName) throws YearOutOfBoundsException {
		for(Member member : members) {
			if(member.getName().equals(userName)) {
				loggedIn = true;
				userMenu(member);
			}		
		}
	}
	
	public void userMenu(Member member) throws YearOutOfBoundsException {
		String input;
		int numWeeks, day, returnHour;
		while(true) {
			System.out.println("Display account info: 1");
			System.out.println("Display vehicle info: 2");
			System.out.println("Display schedules: 3");
			
			System.out.println("Add vehicle: 4");
			System.out.println("Add commute: 5");
			System.out.println("logout: 0");
			
			
			input = userInput.nextLine();
			if(input.equals("1")) {
				System.out.println(member);
			}
			if(input.equals("2") && member.getRegisteredVehicles() != null) {
				System.out.println(member.getRegisteredVehicles());
			}
			if(input.equals("3")) {
				System.out.println(member.getCommuteTimes());
			}
			if(input.equals("4")) {
				System.out.println(member.getCommuteTimes());
				member.getRegisteredVehicles().addVehicle(member.getName() + " vehicle", new Vehicle(Make.FORD, "Fiesta", 1998, "1234abcd", 5));
			}
			if(input.equals("5")) {
				System.out.println("enter a day of the week: 1 = monday, 2 = tuesday...5 = friday");
				day = userInput.nextInt();
				System.out.println("enter number of hours before return");
				returnHour = userInput.nextInt();
				
				System.out.println("enter number of days to repeat stop");
				numWeeks = userInput.nextInt();
				
				member.getCommuteTimes().addWeekly(new Stop(weekdays[day-1], sjsu), numWeeks);
				member.getCommuteTimes().addWeekly(new Stop(Time.timeAfter(weekdays[day-1], returnHour * 3600), member.getAddress()), numWeeks);
			}
			if(input.equals("0")) {
				return;
			}
			
		}
		
	}
	
	public void createSchedule() {
		int numHours;
		Time rightNow;
		System.out.print("choose time frame: between now and any number of hours->");
		numHours = userInput.nextInt();
		ScheduleFactory.schedule((Member[]) members.toArray(), rightNow = new Time(), Time.timeAfter(rightNow, 60 * 60 * numHours), sjsu, new SchedulingPreference(), false);
	}
}
