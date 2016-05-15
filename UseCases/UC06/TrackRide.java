package UC06;

import dev.se133.project.car.Car;
import dev.se133.project.carpool.Carpool;
import dev.se133.project.carpool.CarpoolListener;
import dev.se133.project.commute.Address;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.Stop;
import dev.se133.project.commute.Time;
import dev.se133.project.member.Member;
import dev.se133.project.member.garage.Garage;
import dev.se133.project.member.garage.Vehicle;
import dev.se133.project.member.garage.Vehicle.Make;
import dev.se133.project.member.garage.YearOutOfBoundsException;
import dev.se133.project.member.preferences.CommuteSchedule;
import dev.se133.project.member.wallet.Wallet;

public class TrackRide {	
	public static void main(String[] args) {
		Carpool carpool = buildCarpool();
		
		System.out.println("Created new carpool");
		System.out.println(carpool);
		
		System.out.println("Tracking carpool");;
		
		carpool.addListener(new CarpoolListener() {
			
			@Override
			public void hitStop(Stop currentStop) {
				System.out.println("Hit stop: " + currentStop);
			}
			
			@Override
			public void hitEnd(Stop endStop) {
				System.out.println("Hit end of commute");
			}
			
			@Override
			public void dispatched(Carpool context) {
				System.out.println("Dispatched carpool");
			}
		});
		carpool.dispatch();
		
		while (carpool.nextStop() != null);
	}
	
	private static Carpool buildCarpool() {
		Member[] members = buildMembers();
		
		Commute commute = buildCommute(members);
		Car car = buildCar(members);
		
		Carpool carpool = new Carpool(commute, car);
		
		return carpool;
	}
	private static Member[] buildMembers() {
		Member[] members = new Member[5];
		
		Member driver = new Member(0, "Driver0", true, new Address("Address0"), new Wallet(), new Garage(), new CommuteSchedule());
		try {
			driver.getRegisteredVehicles().addVehicle("DriverVehicle", new Vehicle(Make.HONDA, "Something", 2000, "00000000", 5));
		} catch (YearOutOfBoundsException e) {
			e.printStackTrace();
		}
		members[0] = driver;
		
		for (int i = 1; i < members.length; i++) {
			Member currentPassenger = new Member(i, "Passenger" + i, false, new Address("Address" + i), new Wallet(), new Garage(), new CommuteSchedule());
			
			members[i] = currentPassenger;
		}
		return members;
	}
	private static Commute buildCommute(Member[] members) {
		Commute commute = new Commute();
		
		Time currentTime = new Time();
		
		for (Member member : members) {
			Address currentAddress = member.getAddress();
			currentTime = Time.timeAfter(currentTime, 10 * 60);
			Stop currentStop = new Stop(currentTime, currentAddress);
			
			commute.addStop(currentStop);
		}
		return commute;
	}
	private static Car buildCar(Member[] members) {
		Car car = new Car(members[0]);
		
		for (int i = 1; i < members.length; i++) {
			car.addPassenger(members[i]);
		}
		return car;
	}
}
