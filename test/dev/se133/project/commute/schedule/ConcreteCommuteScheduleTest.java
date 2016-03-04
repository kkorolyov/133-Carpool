package dev.se133.project.commute.schedule;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import dev.se133.project.entity.Address;
import dev.se133.project.entity.Day;
import dev.se133.project.entity.Time;
import dev.se133.project.entity.commute.*;
import dev.se133.project.entity.exception.TimeOutOfBoundsException;
import dev.se133.project.entity.schedule.CommuteSchedule;
import dev.se133.project.entity.schedule.SortedCommuteSchedule;

@SuppressWarnings("javadoc")
public class ConcreteCommuteScheduleTest {
	private static String listString = "CURRENT SCHEDULE", changeString = "CHANGING ALL COMMUTES ON DAY: ", dropString = "DROPPING ALL COMMUTES ON DAY: ", createString = "CREATING NEW COMMUTE";
	
	private SortedCommuteSchedule schedule;

	@Before
	public void setUp() throws Exception {
		schedule = new SortedCommuteSchedule();
		populateSchedule(schedule);
	}

	@Test
	public void testGetAllCommutes() throws TimeOutOfBoundsException {	// TODO Not a test
		listCommutes();
		System.out.println();
		changeCommute();
		System.out.println();
		listCommutes();
		System.out.println();
		dropCommute();
		System.out.println();
		listCommutes();
		System.out.println();
		createCommute();
		System.out.println();
		listCommutes();
	}
	private void listCommutes() {
		System.out.println(listString);
		int counter = 0;
		CommutePoint point;
		for (Commute commute : schedule.getAllCommutes()) {
			System.out.println("Commute " + counter++);
			System.out.println("\tDay: " + commute.getDay());
			
			point = commute.getDeparture();
			System.out.println("\tDeparture");
			System.out.println("\t\tAddress: " + point.getAddress().toString());
			System.out.println("\t\tTime: " + point.getTime().getHour() + ":" + point.getTime().getMinute());
			
			point = commute.getArrival();
			System.out.println("\tArrival");
			System.out.println("\t\tAddress: " + point.getAddress().toString());
			System.out.println("\t\tTime: " + point.getTime().getHour() + ":" + point.getTime().getMinute());
		}
	}
	private void changeCommute() throws TimeOutOfBoundsException {
		Day changeDay = Day.SUNDAY;
		System.out.println(changeString + changeDay);
		for (Commute commute : schedule.getAllCommutes(changeDay))
			commute.setArrival(new CommutePoint(new Address("New Arrival"), new Time(21, 47)));
	}
	private void dropCommute() {
		Day dropDay = Day.MONDAY;
		System.out.println(dropString + dropDay);
		schedule.dropAllCommutes(dropDay);
	}
	private void createCommute() throws TimeOutOfBoundsException {
		Day createDay = Day.FRIDAY;
		CommutePoint departure = new CommutePoint(new Address("Depart"), new Time(11, 14)), arrival = new CommutePoint(new Address("Arrive"), new Time(13, 14));
		System.out.println(createString);
		schedule.scheduleCommute(new Commute(createDay, departure, arrival));
	}

	@Test
	public void testGetAllCommutesDay() {
		fail("Not yet implemented");
	}

	@Test
	public void testScheduleCommute() {
		fail("Not yet implemented");
	}

	@Test
	public void testDropCommute() {
		fail("Not yet implemented");
	}

	@Test
	public void testDropAllCommutes() {
		fail("Not yet implemented");
	}

	@Test
	public void testDropAllCommutesDay() {
		fail("Not yet implemented");
	}

	private static void populateSchedule(CommuteSchedule schedule) throws TimeOutOfBoundsException {
		Address depart = new Address("Depart Address"), arrive = new Address("Arrive Address");
				
		for (Day day : Day.values()) {
			/*for (int hour = Time.HOUR_MIN; hour <= Time.HOUR_MAX; hour++) {
				for (int minute = Time.MINUTE_MIN; minute < Time.MINUTE_MAX; minute++) {
					// TODO All variations
				}
			}*/
		}
		// TODO UGLY CODE BELOW HERE
		schedule.scheduleCommute(new Commute(Day.SUNDAY, new CommutePoint(depart, new Time(10, 10)), new CommutePoint(arrive, new Time(11, 0))));
		schedule.scheduleCommute(new Commute(Day.MONDAY, new CommutePoint(depart, new Time(10, 10)), new CommutePoint(arrive, new Time(11, 17))));
		schedule.scheduleCommute(new Commute(Day.TUESDAY, new CommutePoint(depart, new Time(10, 10)), new CommutePoint(arrive, new Time(11, 21))));
	}
}
