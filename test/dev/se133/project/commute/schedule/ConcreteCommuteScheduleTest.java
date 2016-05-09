package dev.se133.project.commute.schedule;

import static org.junit.Assert.fail;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import dev.se133.project.commute.*;
import dev.se133.project.commute.Time.Day;
import dev.se133.project.commute.Time.Month;
import dev.se133.project.schedule.CommuteSchedule;
import dev.se133.project.schedule.SortedCommuteSchedule;

@SuppressWarnings("javadoc")
public class ConcreteCommuteScheduleTest {
	private static final String listString = "CURRENT SCHEDULE", changeString = "CHANGING ALL COMMUTES ON DAY: ", dropString = "DROPPING ALL COMMUTES ON DAY: ", createString = "CREATING NEW COMMUTE";
	private static final int year = 2016;
	private static final Month month = Month.DECEMBER;
	private static final int second = 0;
	
	private SortedCommuteSchedule schedule;

	@Before
	public void setUp() throws Exception {
		schedule = new SortedCommuteSchedule();
		populateSchedule(schedule);
	}

	@Test
	public void testGetAllCommutes() {	// TODO Not a test
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
		Stop point;
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
	private void changeCommute() {
		Day changeDay = Day.SUNDAY;
		System.out.println(changeString + changeDay);
		for (Commute commute : schedule.getAllCommutes(changeDay))
			commute.addStop(new Stop(new Time(year, month, changeDay, 21, 47, second), new Address("New Arrival")));
	}
	private void dropCommute() {
		Day dropDay = Day.MONDAY;
		System.out.println(dropString + dropDay);
		schedule.dropAllCommutes(dropDay);
	}
	private void createCommute() {
		Day createDay = Day.FRIDAY;
		Stop departure = new Stop(new Time(year, month, createDay, 11, 14, second), new Address("Depart")), arrival = new Stop(new Time(year, month, createDay, 13, 14, second), new Address("Arrive"));
		System.out.println(createString);
		
		Commute commute = new Commute();
		commute.addStop(departure);
		commute.addStop(arrival);
		
		schedule.scheduleCommute(commute);
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

	private static void populateSchedule(CommuteScheduleOLD schedule) {
		Address depart = new Address("Depart Address"), arrive = new Address("Arrive Address");
				
		for (Day day : Day.values()) {
			/*for (int hour = Time.HOUR_MIN; hour <= Time.HOUR_MAX; hour++) {
				for (int minute = Time.MINUTE_MIN; minute < Time.MINUTE_MAX; minute++) {
					// TODO All variations
				}
			}*/
		}
		// TODO UGLY CODE BELOW HERE
		Commute commute1 = new Commute();
		commute1.addStop(new Stop(new Time(year, month, Day.SUNDAY, 10, 10, second), depart));
		commute1.addStop(new Stop(new Time(year, month, Day.SUNDAY, 11, 0, second), arrive));
		
		schedule.scheduleCommute(commute1);
		
		Commute commute2 = new Commute();
		commute2.addStop(new Stop(new Time(year, month, Day.MONDAY, 10, 10, second), depart));
		commute2.addStop(new Stop(new Time(year, month, Day.MONDAY, 11, 17, second), arrive));
		
		schedule.scheduleCommute(commute2);
		
		Commute commute3 = new Commute();
		commute3.addStop(new Stop(new Time(year, month, Day.TUESDAY, 10, 10, second), depart));
		commute3.addStop(new Stop(new Time(year, month, Day.TUESDAY, 11, 21,second ), arrive));
		
		schedule.scheduleCommute(commute3);
	}
}
