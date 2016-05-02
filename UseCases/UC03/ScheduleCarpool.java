package UC03;

import java.util.Random;

import dev.se133.project.commute.Address;
import dev.se133.project.commute.Time;
import dev.se133.project.commute.Time.Day;
import dev.se133.project.member.Member;
import dev.se133.project.member.garage.Garage;
import dev.se133.project.member.garage.Vehicle;
import dev.se133.project.member.garage.Vehicle.Make;
import dev.se133.project.member.garage.YearOutOfBoundsException;
import dev.se133.project.member.preferences.CommutePreference;
import dev.se133.project.member.preferences.CommuteSchedule;
import dev.se133.project.member.wallet.Wallet;
import dev.se133.project.schedule.BasicCarpoolScheduler;
import dev.se133.project.schedule.CarpoolSchedule;

@SuppressWarnings("javadoc")
public class ScheduleCarpool {
	public static double DRIVER_PROBABILITY = .3;
	public static int YEAR_START = 1980,
										YEAR_END = 2016;
	public static int MAX_VIN = 999999999;
	public static int MIN_CAPACITY = 2,
										MAX_CAPACITY = 8;
	public static int MIN_DAY = 1,
										MAX_DAY = 7;
	public static Day COMMUTE_DAY = Day.MONDAY;
	public static Time INITIAL_TIME = new Time();
	public static int MIN_DELAY = 1 * 60,
										MAX_DELAY = 10 * 60;
	public static int DEFAULT_NUM_MEMBERS = 10;
	
	public static Random rand = new Random();

	public static void main(String[] args) throws YearOutOfBoundsException {
		int numMembers = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_NUM_MEMBERS;
		
		CarpoolSchedule schedule = new BasicCarpoolScheduler(buildMembers(numMembers), INITIAL_TIME, Time.timeAfter(INITIAL_TIME, 24 * 60 * 60), null).schedule();
		
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
		Day day = randomDay();
		
		Time[] prefTimes = new Time[4];
		Time lastTime = INITIAL_TIME;
		for (int i = 0; i < prefTimes.length; i++) {
			int randomDelay = randomInterval(MIN_DELAY, MAX_DELAY);
			lastTime = Time.timeAfter(lastTime, randomDelay);
			
			prefTimes[i] = lastTime;
		}
		CommutePreference preference = new CommutePreference(prefTimes[0], prefTimes[1], prefTimes[2], prefTimes[3]);
		
		schedule.addPreference(preference);
		
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
