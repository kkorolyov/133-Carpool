package dev.se133.project.member.wallet;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dev.se133.project.car.Car;
import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.Address;
import dev.se133.project.commute.Commute;
import dev.se133.project.member.garage.*;
import dev.se133.project.commute.Stop;
import dev.se133.project.commute.Time;
import dev.se133.project.commute.Time.Day;
import dev.se133.project.member.Member;
import dev.se133.project.member.garage.Vehicle;
import dev.se133.project.member.garage.YearOutOfBoundsException;
import dev.se133.project.member.garage.Vehicle.Make;
import dev.se133.project.member.preferences.CommuteScheduleOLD;

public class WalletTesterJUnit {
	private static final int NUM_STOPS = 10;
	private static final Day DAY = Day.MONDAY;
	private static final int START_TIME = 7 * 60;
	private static final int CAR_CAPACITY = 5;
	
	private static Commute commute;
	private static Car car;
	
	private Carpool carpool;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		commute = new Commute();
		populateCommute();
		
		car = new Car(CAR_CAPACITY);
		populateCar();
	}
	private static void populateCommute()
	{
		Address currentAddress;
		Time currentTime = new Time();
		for (int i = 0; i < NUM_STOPS; i++) {
			currentAddress = new Address("Stop" + i);
			currentTime = Time.timeAfter(currentTime, i);
			
			commute.addStop(new Stop(currentTime, currentAddress));
		}
	}
	private static void populateCar() 
	{
		int id;
		for (int i = 0; i < car.getCapacity(); i++) 
		{
			id =i+1;
			Member a = new Member(id, "Member " + id, i == 1 ? true : false,
					new Address("MemberAddress " + i), new Wallet(), new Garage(), new CommuteScheduleOLD());
			if(i == 0)
			{
				car.addDriver(a);
				try 
				{
					a.getRegisteredVehicles().addVehicle("Car " + id, new Vehicle(Make.ABARTH, "red", 2015, "v" + i , 5));
				} catch (YearOutOfBoundsException e) 
				{
					e.printStackTrace();
				}
			}
			else
				car.addPassenger(a);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		carpool = new Carpool(commute, car);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNextStop() {
		int stopCounter = 0;
		
		while (carpool.nextStop() != null)
		{
			System.out.println("Stopping " + carpool.nextStop().toString());
			stopCounter++;
			System.out.println("stopCounter: " + stopCounter);
			
		}
		assertEquals(stopCounter, carpool.getCommute().getStops().size());
	}

	/*
	@Test
	public void addRewardListener() {
		carpool.addListener(new CarpoolRewardListener());
		
		System.out.println("All members in carpool");
		printPoints();
		
		int stopCounter = 0;
		while (carpool.nextStop() != null) {
			System.out.println("After stop " + ++stopCounter + " at Address: " + carpool.currentStop().getAddress());
			if (carpool.isAtEnd())
				System.out.println("End stop");
			printPoints();
		}		
	}
	private void printPoints() {
		for (Member member : carpool.getCar().getInhabitants()) {
			System.out.println(	member.getName() + "\n"
												+ "\tDriver: " + member.isDriver() + "\n"
												+ "\tWallet: " + String.valueOf(member.getWallet()) + "\n");
		}
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/

}
