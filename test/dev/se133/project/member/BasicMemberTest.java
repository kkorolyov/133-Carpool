package dev.se133.project.member;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dev.se133.project.commute.Address;
import dev.se133.project.member.Vehicle.Make;
import dev.se133.project.member.car.Car;
import dev.se133.project.schedule.SortedCommuteSchedule;

@SuppressWarnings("javadoc")
public class BasicMemberTest {
	private Member member;

	@Before
	public void setUp() throws Exception {
		member = new BasicMember((int) (Math.random() * 100), "TestMember", new Address("TestMemberAddress"), new SortedCommuteSchedule(), new MemberState.Passenger());
	}

	@After
	public void tearDown() throws Exception {
		//
	}
	
	@Test
	public void testMakeCar() throws Exception {
		String testVehicleName = "TestVehicle";
		Vehicle testVehicle = new Vehicle(Make.AUDI, "TestModel", 2000, "1", 5);
		
		member.getGarage().addVehicle(testVehicleName, testVehicle);
		Car car = member.getCar();
		
		assertTrue(car == null);
		
		member.setState(new MemberState.Driver());
		car = member.getCar();
		assertEquals(member, car.getDriver());
	}

	@Test
	public void testAddListener() {
		member.setState(new MemberState.Passenger());
		
		member.addListener(new MemberListener() {
			@Override
			public void stateChanged(MemberState newState) {
				assertEquals(member.getState(), newState);
				System.out.println("Member '" + member.getName() + "' state changed to " + newState.getStateName());
			}
		});
		
		member.setState(new MemberState.Driver());
		member.setState(new MemberState.Passenger());
		member.setState(new MemberState.Driver());
	}

	@Test
	public void testCompareTo() {
		fail("Not yet implemented");
	}

}
