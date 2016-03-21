package dev.se133.project.scheduler;

import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dev.se133.project.commute.*;
import dev.se133.project.commute.Time.Day;
import dev.se133.project.commute.Time.Month;
import dev.se133.project.map.AddressMap;
import dev.se133.project.map.ArrayAddressMap;
import dev.se133.project.member.*;
import dev.se133.project.member.car.Car;
import dev.se133.project.member.car.FullCarException;
import dev.se133.project.member.car.NoDriverException;
import dev.se133.project.schedule.SortedCommuteSchedule;
import dev.se133.project.scheduler.SimpleScheduler;

public class SimpleSchedulerTest {
	private static final int xSize = 100, ySize = 100;
	private static AddressMap map;
	private static Car car;
	private static Stop departure, arrival;
	
	private ScheduleContext scheduler;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		map = buildMap(6);
		
		Member[] members = buildMembers(map);
		
		arrival = new Stop(Time.timeAfter(departure.getTime(), 60 * 60), new Address("Address0"));
		
		car = buildCar(members);
	}
	
	@Before
	public void setUp() throws Exception {
		scheduler = new ScheduleContext(map, car, departure, arrival);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSchedule() throws NoDriverException {
		scheduler.createSchedule();
		int i = 0;
		Commute commute = scheduler.getCommute();
		System.out.println(map);
		for(Member member : car.getInhabitants()) {
			if(member.getState().getStateName().equals("Driver"))
				System.out.println("Member: " + member.getName() + "\t\t" + member.getState().getStateName() + "   \tAddress: " + member.getAddress().toString());
			else
				System.out.println("Member: " + member.getName() + "\t\t" + member.getState().getStateName() + "\tAddress: " + member.getAddress().toString());
		}
		
		System.out.println("\nDay of commute: " + commute.getStart().getTime().getDay());
		for (Stop stop : commute.getStops()) {
			if(i == 0) {
				System.out.println("\tDeparture time: " + stop.getTime().getHour() + ":" + stop.getTime().getMinute());
				System.out.println("\tFrom departure address: " + stop.getAddress().toString() + "\n");
			}
			else if(i < commute.getStops().size() - 1) {
				System.out.println("\tStop time: " + stop.getTime().getHour() + ":" + stop.getTime().getMinute());
				System.out.println("\tAt address: " + stop.getAddress().toString() + "\n");
			}
			else {
				System.out.println("\tDestination time: " + stop.getTime().getHour() + ":" + stop.getTime().getMinute());
				System.out.println("\tAt final address: " + stop.getAddress().toString() + "\n");
			}
			i++;
		}
	}

	private static Member[] buildMembers(AddressMap map)  {
		Set<Address> addresses = map.getAllAddresses();
		Member[] members = new Member[addresses.size() - 1];
		
		int counter = 0;
		for (Address address : addresses) {
			if (counter < members.length)
				members[counter++] = new BasicMember(counter, "Member" + (counter - 1), address, new SortedCommuteSchedule());
		}
		members[0].setState(new MemberState.Driver());	// 1 driver
		departure = new Stop(new Time(2016, Month.JANUARY, Day.MONDAY, 12, 10, 0), members[0].getAddress());
		
		return members;
	}
	
	private static AddressMap buildMap(int numAddresses) {
		int xSize = numAddresses * 2, ySize = xSize;	// Half of map empty
		ArrayAddressMap map = new ArrayAddressMap(xSize, ySize);
		
		Random random = new Random();
		for (int i = 0; i < numAddresses; i++) {
			while (!map.addAddress(new Address("Address" + i), random.nextInt(xSize), random.nextInt(ySize)));	// Keep randomizing until an empty spot found
		}
		return map;
	}
	
	private static Car buildCar(Member[] members) throws FullCarException, NoDriverException {
		Car car = new Car(members.length);
		
		for (Member member : members) {
			car.addPassenger(member);
			
			car.setDriver();
		}
		return car;
	}
}
