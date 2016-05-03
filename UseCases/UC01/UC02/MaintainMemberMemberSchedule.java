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
		System.out.println("Creating new member Bob Dole with no schedule or vehicles.");
		Member bob = new Member(0, "Bob Dole", false, new Address("123 North St"), new Wallet(), null, null);
		System.out.println(bob.getName());
		Garage bobsGarage = new Garage();
		System.out.println("Adding a vehicle, Ford Fiesta, to member Bob");
		bobsGarage.addVehicle("fiesta", new Vehicle(Make.FORD, "Fiesta", 1998, "1234abcd", 5));
		bob.setRegisteredVehicles(bobsGarage);
		System.out.println(bob.getRegisteredVehicles().toString());
		
		System.out.println("Creating and adding a schedule for a single day, today, commute for Bob");
		CommuteSchedule bobSchedule = new CommuteSchedule();
		Time preferredLeavingTime = new Time();
		Time preferredArrivalTime = Time.timeAfter(preferredLeavingTime, 3600);
		Time returnLeavingTime = Time.timeAfter(preferredLeavingTime, 3600);
		Time returnArrivalTime = Time.timeAfter(returnLeavingTime, 3600);
		bobSchedule.addPreference(new CommutePreference(preferredLeavingTime, preferredArrivalTime, returnLeavingTime, returnArrivalTime));
		System.out.println(bobSchedule.getPreference(preferredArrivalTime.getDay()).toString());
		System.out.println("Removing today's commute");
		bobSchedule.removePreference(preferredArrivalTime.getDay());
		
		System.out.println("Creating and adding a schedule to be repeated weekly, starting today, commute for Bob");

		preferredLeavingTime = new Time();
		preferredArrivalTime = Time.timeAfter(preferredLeavingTime, 3600);
		returnLeavingTime = Time.timeAfter(preferredArrivalTime, 3600);
		returnArrivalTime = Time.timeAfter(returnLeavingTime, 3600);
		bobSchedule.addPreferenceRepeated(new CommutePreference(preferredLeavingTime, preferredArrivalTime, returnLeavingTime, returnArrivalTime), preferredLeavingTime, 5);
		
		Map<Time, CommutePreference> preferences = bobSchedule.getPreferences();
		for(Time time : preferences.keySet()) {
			System.out.println(preferences.get(time));//.getTime(CommutePreference.TO_DESTINATION));
		}
		
		bobSchedule.clear();
		
		System.out.println("Creating and adding a Monday-Friday schedule, to be repeated weekly, commute for Bob");

		Time mondayTime = new Time(2016, Month.MAY, Day.MONDAY, 8, 0, 0, 9);
		Time tuesdayTime = Time.timeAfter(mondayTime, 86400);
		Time wednesdayTime = Time.timeAfter(tuesdayTime, 86400);
		Time thursdayTime = Time.timeAfter(wednesdayTime, 86400);
		Time fridayTime = Time.timeAfter(thursdayTime, 86400);
		preferredArrivalTime = Time.timeAfter(preferredLeavingTime, 3600);
		returnLeavingTime = Time.timeAfter(preferredArrivalTime, 3600);
		returnArrivalTime = Time.timeAfter(returnLeavingTime, 3600);
		bobSchedule.addPreferenceRepeated(new CommutePreference(mondayTime, Time.timeAfter(mondayTime, 3600), Time.timeAfter(mondayTime, 32400), Time.timeAfter(mondayTime, 36000)), mondayTime, 5);
		bobSchedule.addPreferenceRepeated(new CommutePreference(tuesdayTime, Time.timeAfter(tuesdayTime, 3600), Time.timeAfter(tuesdayTime, 32400), Time.timeAfter(tuesdayTime, 36000)), tuesdayTime, 5);
		bobSchedule.addPreferenceRepeated(new CommutePreference(wednesdayTime, Time.timeAfter(wednesdayTime, 3600), Time.timeAfter(wednesdayTime, 32400), Time.timeAfter(wednesdayTime, 36000)), wednesdayTime, 5);
		bobSchedule.addPreferenceRepeated(new CommutePreference(thursdayTime, Time.timeAfter(thursdayTime, 3600), Time.timeAfter(thursdayTime, 32400), Time.timeAfter(thursdayTime, 36000)), thursdayTime, 5);
		bobSchedule.addPreferenceRepeated(new CommutePreference(fridayTime, Time.timeAfter(fridayTime, 3600), Time.timeAfter(fridayTime, 32400), Time.timeAfter(fridayTime, 36000)), fridayTime, 5);
		for(Time time : preferences.keySet()) {
			System.out.println(preferences.get(time));//.getTime(CommutePreference.TO_DESTINATION));
		}
	}

}
