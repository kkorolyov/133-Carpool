package UC03;

import java.util.Random;

import dev.se133.project.commute.Address;
import dev.se133.project.commute.Stop;
import dev.se133.project.commute.Time;
import dev.se133.project.commute.Time.Day;
import dev.se133.project.member.Member;
import dev.se133.project.member.garage.Garage;
import dev.se133.project.member.garage.Vehicle;
import dev.se133.project.member.garage.Vehicle.Make;
import dev.se133.project.member.garage.YearOutOfBoundsException;
import dev.se133.project.member.preferences.CommuteSchedule;
import dev.se133.project.member.wallet.Wallet;
import dev.se133.project.schedule.CarpoolSchedule;
import dev.se133.project.schedule.ScheduleFactory;
import dev.se133.project.schedule.SchedulingPreference;

@SuppressWarnings("javadoc")
public class ScheduleCarpool {
	public static final Address DESTINATION = new Address("Final DestinationLand");
	public static final double DRIVER_PROBABILITY = .3;
	public static final int YEAR_START = 1980,
													YEAR_END = 2016;
	public static final int MAX_VIN = 999999999;
	public static final int MIN_CAPACITY = 2,
													MAX_CAPACITY = 8;
	public static final int MIN_DAY = 1,
													MAX_DAY = 7;
	public static final Day COMMUTE_DAY = Day.MONDAY;
	public static final Time INITIAL_TIME = new Time();
	public static final int MIN_DELAY = 1 * 60,
													MAX_DELAY = 10 * 60;
	public static final int DEFAULT_NUM_MEMBERS = 10;
	
	public static Random rand = new Random();

	public static void main(String[] args) throws YearOutOfBoundsException {
		int numMembers = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_NUM_MEMBERS;
		
		Member[] members = buildMembers(numMembers);
		Time 	start = INITIAL_TIME,
					end = Time.timeAfter(INITIAL_TIME, 24 * 60 * 60);
		SchedulingPreference preferences = null;
		boolean driverPref = false;
		
		CarpoolSchedule schedule = ScheduleFactory.schedule(members, start, end, DESTINATION, preferences, driverPref);
		
		System.out.println(schedule);
	}
	private static Member[] buildMembers(int numMembers) throws YearOutOfBoundsException {
		Member[] members = new Member[numMembers];
		
		for (int i = 0; i < members.length; i++) {
			boolean currentDriverStatus = randomizeIsDriver();
			Address currentAddress = new Address("MemberAddress" + i);
			Wallet currentWallet = new Wallet();
			Garage currentRegisteredVehicles = buildGarage(currentDriverStatus);
			CommuteSchedule currentCommuteTimes = buildCommuteTimes();
			
			Member currentMember = new Member(i, "Member" + i, currentDriverStatus, currentAddress, currentWallet, currentRegisteredVehicles, currentCommuteTimes);
			
			members[i] = currentMember;
		}
		return members;
	}
	private static boolean randomizeIsDriver() {
		return Math.random() <= DRIVER_PROBABILITY ? true : false;
	}
	
	private static Garage buildGarage(boolean isDriver) throws YearOutOfBoundsException {
		Garage garage = new Garage();
		
		if (isDriver) {
			garage.addVehicle("Vehicle", new Vehicle(randomMake(), "Something", randomYear(), randomVin(), randomCapacity()));
		}
		return garage;
	}
	private static Make randomMake() {		
		return Make.values()[rand.nextInt(Make.values().length)];
	}
	private static int randomYear() {
		return randomInterval(YEAR_START, YEAR_END);
	}
	private static String randomVin() {
		return String.valueOf(rand.nextInt(MAX_VIN + 1));
	}
	private static int randomCapacity() {
		return randomInterval(MIN_CAPACITY, MAX_CAPACITY);
	}
	
	private static CommuteSchedule buildCommuteTimes() {
		CommuteSchedule schedule = new CommuteSchedule();
			
		Time time = Time.timeAfter(INITIAL_TIME, randomInterval(1, 10) * 60);
		Stop stop = new Stop(time, DESTINATION);
		
		schedule.add(stop);
		
		return schedule;
	}
	private static Day randomDay() {
		return Day.values()[randomInterval(MIN_DAY, MAX_DAY)];
	}
	
	private static int randomInterval(int start, int end) {
		int diff = end - start;
		
		return rand.nextInt(diff) + start;
	}
}
