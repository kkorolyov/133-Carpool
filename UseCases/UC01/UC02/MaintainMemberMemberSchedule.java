package UC01.UC02;

import dev.se133.project.commute.Address;
import dev.se133.project.member.*;
import dev.se133.project.member.garage.Garage;
import dev.se133.project.member.garage.Vehicle;
import dev.se133.project.member.garage.Vehicle.Make;
import dev.se133.project.member.garage.YearOutOfBoundsException;
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
		
		System.out.println("Creating and adding a schedule to Bob");
		
	}

}
