package UC01.UC02;

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

public class MaintainMemberMemberSchedule {
	private static final Address SJSU = new Address("SJSU");
	private static final Address HOME = new Address("HOME");

	public static void main(String[] args) throws YearOutOfBoundsException {
		Member[] members = new Member[20];
		System.out.println("Creating 20 members including 5 drivers with vehicles.");
		for(int i = 0; i < 20; i ++) {
			if(i % 4 == 0) {
				members[i] = new Member(0, "member " + i, true, new Address("address " + i), new Wallet(), new Garage(), new CommuteSchedule());
				members[i].getRegisteredVehicles().addVehicle("vehicle " + i, new Vehicle(Make.FORD, "Fiesta", 1998, "1234abcd", 5));
			}
			else {
				members[i] = new Member(0, "member " + i, false, new Address("address " + i), new Wallet(), null, new CommuteSchedule());
				Time mondayTime = new Time(2016, Month.MAY, Day.MONDAY, 8, 0, 0, 9);
				Time tuesdayTime = Time.timeAfter(mondayTime, 86400);
				Time wednesdayTime = Time.timeAfter(tuesdayTime, 86400);
				Time thursdayTime = Time.timeAfter(wednesdayTime, 86400);
				Time fridayTime = Time.timeAfter(thursdayTime, 86400);
				members[i].getCommuteTimes().addWeekly(new Stop(mondayTime, SJSU), 5);
				members[i].getCommuteTimes().addWeekly(new Stop(Time.timeAfter(mondayTime, 32400), HOME), 5);
				members[i].getCommuteTimes().addWeekly(new Stop(tuesdayTime, SJSU), 5);
				members[i].getCommuteTimes().addWeekly(new Stop(Time.timeAfter(tuesdayTime, 32400), HOME), 5);
				members[i].getCommuteTimes().addWeekly(new Stop(wednesdayTime, SJSU), 5);
				members[i].getCommuteTimes().addWeekly(new Stop(Time.timeAfter(wednesdayTime, 32400), HOME), 5);
				members[i].getCommuteTimes().addWeekly(new Stop(thursdayTime, SJSU), 5);
				members[i].getCommuteTimes().addWeekly(new Stop(Time.timeAfter(thursdayTime, 32400), HOME), 5);
				members[i].getCommuteTimes().addWeekly(new Stop(fridayTime, SJSU), 5);
				members[i].getCommuteTimes().addWeekly(new Stop(Time.timeAfter(fridayTime, 32400), HOME), 5);
			}
		}
		for (Member member : members) {			
			System.out.println("Schedule for " + member.getName());
			System.out.println(member.getCommuteTimes());
			
			member.getCommuteTimes().clear();
		}
		
		System.out.println("Creating schedules that don't match up for the first five members");
		for(int i = 0; i < members.length; i++) {
			Time mondayTime = new Time(2016, Month.MAY, Day.MONDAY, 8, i*5, 0, 9);
			Time tuesdayTime = Time.timeAfter(mondayTime, 86400);
			Time wednesdayTime = Time.timeAfter(tuesdayTime, 86400);
			Time thursdayTime = Time.timeAfter(wednesdayTime, 86400);
			Time fridayTime = Time.timeAfter(thursdayTime, 86400);
			members[i].getCommuteTimes().addWeekly(new Stop(mondayTime, SJSU), 5);
			members[i].getCommuteTimes().addWeekly(new Stop(Time.timeAfter(mondayTime, 32400), HOME), 5);
			members[i].getCommuteTimes().addWeekly(new Stop(tuesdayTime, SJSU), 5);
			members[i].getCommuteTimes().addWeekly(new Stop(Time.timeAfter(tuesdayTime, 32400), HOME), 5);
			members[i].getCommuteTimes().addWeekly(new Stop(wednesdayTime, SJSU), 5);
			members[i].getCommuteTimes().addWeekly(new Stop(Time.timeAfter(wednesdayTime, 32400), HOME), 5);
			members[i].getCommuteTimes().addWeekly(new Stop(thursdayTime, SJSU), 5);
			members[i].getCommuteTimes().addWeekly(new Stop(Time.timeAfter(thursdayTime, 32400), HOME), 5);
			members[i].getCommuteTimes().addWeekly(new Stop(fridayTime, SJSU), 5);
			members[i].getCommuteTimes().addWeekly(new Stop(Time.timeAfter(fridayTime, 32400), HOME), 5);
		}
		for (Member member : members) {			
			System.out.println("Schedule for " + member.getName());
			System.out.println(member.getCommuteTimes());
			
			member.getCommuteTimes().clear();
		}
	}
}
