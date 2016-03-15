package dev.se133.project.commute.schedule;

import static org.junit.Assert.fail;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import dev.se133.project.commute.*;
import dev.se133.project.schedule.CommuteSchedule;
import dev.se133.project.schedule.SortedCommuteSchedule;

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
			System.out.println("\tDay: " + commute.getStart().getTime().getDay());
			
			point = commute.getStart();
			System.out.println("\tDeparture");
			System.out.println("\t\tAddress: " + point.getAddress().toString());
			System.out.println("\t\tTime: " + point.getTime().getHour() + ":" + point.getTime().getMinute());
			
			point = commute.getEnd();
			System.out.println("\tArrival");
			System.out.println("\t\tAddress: " + point.getAddress().toString());
			System.out.println("\t\tTime: " + point.getTime().getHour() + ":" + point.getTime().getMinute());
		}
	}
	private void changeCommute() throws TimeOutOfBoundsException {
		Day changeDay = Day.SUNDAY;
		System.out.println(changeString + changeDay);
		for (Commute commute : schedule.getAllCommutes(changeDay))
			commute.addStop(new CommutePoint(new Address("New Arrival"), new Time(changeDay, 21, 47)));
	}
	private void dropCommute() {
		Day dropDay = Day.MONDAY;
		System.out.println(dropString + dropDay);
		schedule.dropAllCommutes(dropDay);
	}
	private void createCommute() throws TimeOutOfBoundsException {
		Day createDay = Day.FRIDAY;
		CommutePoint departure = new CommutePoint(new Address("Depart"), new Time(createDay, 11, 14)), arrival = new CommutePoint(new Address("Arrive"), new Time(createDay, 13, 14));
		System.out.println(createString);
		
		Set<CommutePoint> stopSet = new TreeSet<>();
		stopSet.add(departure);
		stopSet.add(arrival);
		
		schedule.scheduleCommute(new Commute(stopSet));
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
		Set<CommutePoint> commuteSet1 = new TreeSet<>();
		commuteSet1.add(new CommutePoint(depart, new Time(Day.SUNDAY, 10, 10)));
		commuteSet1.add(new CommutePoint(arrive, new Time(Day.SUNDAY, 11, 0)));
		
		schedule.scheduleCommute(new Commute(commuteSet1));
		
		Set<CommutePoint> commuteSet2 = new TreeSet<>();
		commuteSet2.add(new CommutePoint(depart, new Time(Day.MONDAY, 10, 10)));
		commuteSet2.add(new CommutePoint(arrive, new Time(Day.MONDAY, 11, 17)));
		
		schedule.scheduleCommute(new Commute(commuteSet2));
		
		Set<CommutePoint> commuteSet3 = new TreeSet<>();
		commuteSet3.add(new CommutePoint(depart, new Time(Day.TUESDAY, 10, 10)));
		commuteSet3.add(new CommutePoint(arrive, new Time(Day.TUESDAY, 11, 21)));
		
		schedule.scheduleCommute(new Commute(commuteSet3));
	}
}
