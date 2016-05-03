package UC01.UC02;

import java.util.GregorianCalendar;
import java.util.Map;

import dev.se133.project.commute.Address;
import dev.se133.project.commute.Time;
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
		bobSchedule.addPreferenceRepeatedWeekly(new CommutePreference(preferredLeavingTime, preferredArrivalTime, returnLeavingTime, returnArrivalTime), 5);
		
		Map<Time, CommutePreference> preferences = bobSchedule.getPreferences();
		for(Time time : preferences.keySet()) {
			System.out.println(preferences.get(time));//.getTime(CommutePreference.TO_DESTINATION));
		}
	}

}
