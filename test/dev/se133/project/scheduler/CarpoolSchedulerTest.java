package dev.se133.project.scheduler;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

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
import dev.se133.project.pool.MemberPool;
import dev.se133.project.router.GreedyCommuteBuilder;
import dev.se133.project.schedule.SortedCommuteSchedule;

public class CarpoolSchedulerTest {
	private static final int NUM_MEMBERS = 50;
	
	private static Time startTime, endTime;
	private static MemberPool members;
	private static AddressMap map;
	private static CommutePoint end;
	
	private CarpoolScheduler scheduler;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		buildTimes();
		map = buildMap(NUM_MEMBERS);
		members = buildMemberPool(NUM_MEMBERS);
		end = buildEnd();
	}
	
	@Before
	public void setUp() throws Exception {
		scheduler = new CarpoolScheduler(members, map, end);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSchedule() throws FullCarException, NoDriverException {
		System.out.println(map);
		System.out.println("Start carpools:");
		for (Carpool carpool : scheduler.getAllCarpools()) {
			System.out.println("Members");
			for (Member member : carpool.getCar().getInhabitants()) {
				System.out.println("\t" + member.getName() + ";\t" + member.getState().getStateName() + ";\t" + member.getAddress());
			}
		}
		scheduler.schedule(new GreedyCommuteBuilder());
		System.out.println("Scheduled carpools:");
		for (Carpool carpool : scheduler.getAllCarpools()) {
			CommutePoint start = carpool.getCommute().getStart();
			System.out.println("Carpool starting at " + start.getAddress().toString() + " at " + start.getTime());
			System.out.println("Members");
			for (Member member : carpool.getCar().getInhabitants()) {
				System.out.println("\t" + member.getName() + ";\t" + member.getState().getStateName() + ";\t" + member.getAddress());
			}
			System.out.println();
		}
	}

	@Test
	public void testGetAllCarpools() {
		fail("Not yet implemented");
	}

	private static void buildTimes() throws TimeOutOfBoundsException {
		startTime = new Time(Day.MONDAY, 0, 0);
		endTime = Time.timeAfter(startTime, 60);
	}
	private static MemberPool buildMemberPool(int numMembers) throws TimeOutOfBoundsException {
		int numDrivers = numMembers / 5,
				numPassengers = numMembers - numDrivers - 1;	// Not all cars will be full
		MemberPool pool = new MemberPool();
		
		Set<Address> addressSet = map.getAllAddresses();
		Address[] addressArray = addressSet.toArray(new Address[addressSet.size()]);
		Arrays.sort(addressArray, new Comparator<Address>() {
			@Override
			public int compare(Address o1, Address o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
		for (int i = 0; i < numDrivers; i++) {
			SortedCommuteSchedule currentMemberSchedule = new SortedCommuteSchedule();
			
			Commute currentMemberCommute = new Commute();
			currentMemberCommute.addStop(new CommutePoint(new Address("Stop1"), new Time(Day.MONDAY, i)));
			
			currentMemberSchedule.scheduleCommute(currentMemberCommute);
			
			pool.addMember(new BasicMember(i, "Driver" + i, addressArray[i], currentMemberSchedule, new MemberState.Driver()));
		}
		for (int i = 0; i < numPassengers; i++) {
			SortedCommuteSchedule currentMemberSchedule = new SortedCommuteSchedule();
			
			Commute currentMemberCommute = new Commute();
			currentMemberCommute.addStop(new CommutePoint(new Address("Stop1"), new Time(Day.MONDAY, i + numDrivers)));
			
			currentMemberSchedule.scheduleCommute(currentMemberCommute);
			
			pool.addMember(new BasicMember(i + numDrivers, "Passenger" + i, addressArray[i + numDrivers], currentMemberSchedule, new MemberState.Passenger()));
		}
		return pool;
	}
	private static AddressMap buildMap(int numAddresses) {
		ArrayAddressMap map = new ArrayAddressMap(numAddresses/2, numAddresses/2);
		Random random = new Random();

		for (int i = 0; i < numAddresses; i++) {
			Address currentAddress = new Address("TestAddress" + i);
			
			while(!map.addAddress(currentAddress, random.nextInt(numAddresses/2), random.nextInt(numAddresses/2)));	// Loop until successfully add new address at random location
		}
		
		return map;
	}
	private static CommutePoint buildEnd() {
		return new CommutePoint(new Address("TestAddressEnd"), endTime);
	}
}
