package dev.se133.project.function;

import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dev.se133.project.commute.*;
import dev.se133.project.map.AddressMap;
import dev.se133.project.map.ArrayAddressMap;
import dev.se133.project.member.BasicMember;
import dev.se133.project.member.Member;
import dev.se133.project.member.MemberState;

public class SimpleSchedulerTest {
	private static final int xSize = 100, ySize = 100;
	private static AddressMap map;
	private static Car car;
	private static CommutePoint departure, arrival;
	
	private SimpleScheduler scheduler;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {		
		Member[] members = buildMembers(5);
		
		departure = new CommutePoint(members[0].getAddress(), Day.MONDAY, new Time(12, 0));
		arrival = new CommutePoint(new Address("Arrival"), departure.getDay(), new Time(departure.getTime().getTotalMinutes() + 60));
		
		map = buildMap(members, arrival);
		car = buildCar(members);
	}
	
	@Before
	public void setUp() throws Exception {
		scheduler = new SimpleScheduler(map, car, departure, arrival);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSchedule() {
		scheduler.schedule();
		
		Commute commute = scheduler.getCommute();
		
		System.out.println("Day: " + commute.getDay());
		for (CommutePoint stop : commute.getStops()) {
			System.out.println("\tTime: " + stop.getTime().getHour() + ":" + stop.getTime().getMinute());
			System.out.println("\tAddress: " + stop.getAddress().toString());
		}
	}

	private static Member[] buildMembers(int numMembers) {
		Member[] members = new Member[numMembers];
		for (int i = 0; i < members.length; i++) {
			members[i] = new BasicMember(i, "Member" + i, new Address("Address" + i));
		}
		members[0].setState(new MemberState.Driver());	// 1 driver
		return members;
	}
	
	private static AddressMap buildMap(Member[] members, CommutePoint arrival) {
		ArrayAddressMap map = new ArrayAddressMap(xSize, ySize);
		
		Random random = new Random();
		for (Member member : members) {
			map.addAddress(member.getAddress(), random.nextInt(xSize), random.nextInt(ySize));
		}
		map.addAddress(arrival.getAddress(), random.nextInt(xSize), random.nextInt(ySize));
		
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
