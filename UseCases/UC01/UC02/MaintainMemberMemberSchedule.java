package UC01.UC02;

import java.util.Map;

import dev.se133.project.commute.Address;
import dev.se133.project.commute.Time;
import dev.se133.project.commute.Time.Day;
import dev.se133.project.commute.Time.Month;
import dev.se133.project.member.*;
import dev.se133.project.member.garage.Garage;
import dev.se133.project.member.garage.Vehicle;
import dev.se133.project.member.garage.Vehicle.Make;
import dev.se133.project.member.garage.YearOutOfBoundsException;
import dev.se133.project.member.preferences.CommutePreference;
import dev.se133.project.member.preferences.CommuteSchedule;
import dev.se133.project.member.wallet.Wallet;

public class MaintainMemberMemberSchedule {

	public static void main(String[] args) throws YearOutOfBoundsException {
		Member[] members = new Member[20];
		System.out.println("Creating 20 members including 5 drivers with vehicles.");
		for(int i = 0; i < 20; i ++) {
			if(i < 5) {
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
				members[i].getCommuteTimes().addPreferenceRepeated(new CommutePreference(mondayTime, Time.timeAfter(mondayTime, 3600), Time.timeAfter(mondayTime, 32400), Time.timeAfter(mondayTime, 36000)), mondayTime, 5);
				members[i].getCommuteTimes().addPreferenceRepeated(new CommutePreference(tuesdayTime, Time.timeAfter(tuesdayTime, 3600), Time.timeAfter(tuesdayTime, 32400), Time.timeAfter(tuesdayTime, 36000)), tuesdayTime, 5);
				members[i].getCommuteTimes().addPreferenceRepeated(new CommutePreference(wednesdayTime, Time.timeAfter(wednesdayTime, 3600), Time.timeAfter(wednesdayTime, 32400), Time.timeAfter(wednesdayTime, 36000)), wednesdayTime, 5);
				members[i].getCommuteTimes().addPreferenceRepeated(new CommutePreference(thursdayTime, Time.timeAfter(thursdayTime, 3600), Time.timeAfter(thursdayTime, 32400), Time.timeAfter(thursdayTime, 36000)), thursdayTime, 5);
				members[i].getCommuteTimes().addPreferenceRepeated(new CommutePreference(fridayTime, Time.timeAfter(fridayTime, 3600), Time.timeAfter(fridayTime, 32400), Time.timeAfter(fridayTime, 36000)), fridayTime, 5);
			}
		}
		
		System.out.println("Creating schedules that don't match up for the first five members");
		for(int i = 0; i <  5; i++) {
			Time mondayTime = new Time(2016, Month.MAY, Day.MONDAY, 8, i*5, 0, 9);
			Time tuesdayTime = Time.timeAfter(mondayTime, 86400);
			Time wednesdayTime = Time.timeAfter(tuesdayTime, 86400);
			Time thursdayTime = Time.timeAfter(wednesdayTime, 86400);
			Time fridayTime = Time.timeAfter(thursdayTime, 86400);
			members[i].getCommuteTimes().addPreferenceRepeated(new CommutePreference(mondayTime, Time.timeAfter(mondayTime, 3600), Time.timeAfter(mondayTime, 32400), Time.timeAfter(mondayTime, 36000)), mondayTime, 5);
			members[i].getCommuteTimes().addPreferenceRepeated(new CommutePreference(tuesdayTime, Time.timeAfter(tuesdayTime, 3600), Time.timeAfter(tuesdayTime, 32400), Time.timeAfter(tuesdayTime, 36000)), tuesdayTime, 5);
			members[i].getCommuteTimes().addPreferenceRepeated(new CommutePreference(wednesdayTime, Time.timeAfter(wednesdayTime, 3600), Time.timeAfter(wednesdayTime, 32400), Time.timeAfter(wednesdayTime, 36000)), wednesdayTime, 5);
			members[i].getCommuteTimes().addPreferenceRepeated(new CommutePreference(thursdayTime, Time.timeAfter(thursdayTime, 3600), Time.timeAfter(thursdayTime, 32400), Time.timeAfter(thursdayTime, 36000)), thursdayTime, 5);
			members[i].getCommuteTimes().addPreferenceRepeated(new CommutePreference(fridayTime, Time.timeAfter(fridayTime, 3600), Time.timeAfter(fridayTime, 32400), Time.timeAfter(fridayTime, 36000)), fridayTime, 5);	
		}
	}

}
