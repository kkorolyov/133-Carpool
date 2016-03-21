package dev.se133.project.commute;

import static org.junit.Assert.*;

import org.junit.Test;

import dev.se133.project.commute.Time;
import dev.se133.project.commute.Time.Day;
import dev.se133.project.commute.Time.Month;

@SuppressWarnings("javadoc")
public class TimeTest {
	private static final int 	LOWER_YEAR = 1, UPPER_YEAR = 2,
														LOWER_HOUR = 0, UPPER_HOUR = 23,
														LOWER_MINUTE = 0, UPPER_MINUTE = 59,
														LOWER_SECOND = 0, UPPER_SECOND = 59;
	
	private Time time;

	@Test
	public void testTime() {
		time = new Time();
		System.out.println(time.toString());
	}

	@Test
	public void testTimeParams() {
		for (int testYear = LOWER_YEAR; testYear <= UPPER_YEAR; testYear++) {
			for (Month testMonth : Time.Month.values()) {
				for (Day testDay : Time.Day.values()) {
					for (int testHour = LOWER_HOUR; testHour <= UPPER_HOUR; testHour++) {
						for (int testMinute = LOWER_MINUTE; testMinute <= UPPER_MINUTE; testMinute++) {
							for (int testSecond = LOWER_SECOND; testSecond <= UPPER_SECOND; testSecond++) {
								time = new Time(testYear, testMonth, testDay, testHour, testMinute, testSecond);
								
								assertEquals(testYear, time.getYear());
								assertEquals(testMonth, time.getMonth());
								assertEquals(testDay, time.getDay());
								assertEquals(testHour, time.getHour());
								assertEquals(testMinute, time.getMinute());
								assertEquals(testSecond, time.getSecond());
							}
						}
					}
				}
			}
		}
	}

	@Test
	public void testTimeAfter() {
		int initialYear = 2000;
		Month initialMonth = Time.Month.FEBRUARY;
		Day initialDay = Time.Day.FRIDAY;
		int initialHour = 2;
		int initialMinute = 5;
		int initialSecond = 30;
		Time testInitialTime = new Time(initialYear, initialMonth, initialDay, initialHour, initialMinute, initialSecond);
		
		int testHoursAfter = 14;
		int testSecondsAfter = testHoursAfter * 60 * 60;
		
		Time testTimeAfter = Time.timeAfter(testInitialTime, testSecondsAfter);
		
		assertTrue(testTimeAfter.getHour() == testInitialTime.getHour() + testHoursAfter);
	}
}
