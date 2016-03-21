package dev.se133.project.commute;

import java.util.Calendar;

/**
 * Describes a unique, fixed point in time.
 */
public class Time implements Comparable<Time> {
	private static final char TO_STRING_DELIMETER = '.';
	
	private Calendar calendar;
	
	/**
	 * Returns a time at a specified number of seconds after a given time
	 * @param initialTime initial time
	 * @param secondsAfter seconds to add to initial time
	 * @return time occurring {@code secondsAfter} seconds after {@code initialTime}
	 */
	public static Time timeAfter(Time initialTime, int secondsAfter) {
		int initialYear = initialTime.getYear();
		Month initialMonth = initialTime.getMonth();
		Day initialDay = initialTime.getDay();
		int initialHour = initialTime.getHour();
		int initialMinute = initialTime.getMinute();
		int initialSecond = initialTime.getSecond();
		
		Time newTime = new Time(initialYear, initialMonth, initialDay, initialHour, initialMinute, initialSecond);
		
		newTime.calendar.add(Calendar.SECOND, secondsAfter);
		
		return newTime;
	}
	
	/**
	 * Constructs a time matching the current system time.
	 */
	public Time() {
		calendar = Calendar.getInstance();
	}
	/**
	 * Constructs a time at a custom point in time
	 * @param year any year
	 * @param month any of 12 possible months
	 * @param day any of 7 possible days of the week
	 * @param hour hours from 0-23
	 * @param minute minute from 0-59
	 * @param second second from 0-59
	 */
	public Time(int year, Month month, Day day, int hour, int minute, int second) {
		this();
		
		setYear(year);
		setMonth(month);
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setSecond(second);
	}
	
	/** @return year */
	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}
	/**
	 * Sets this time's year.
	 * @param year new year to set
	 * @return this object
	 */
	public Time setYear(int year) {
		calendar.set(Calendar.YEAR, year);
		
		return this;
	}
	
	/** @return month */
	public Month getMonth() {
		int monthOrdinal = calendar.get(Calendar.MONTH);
		
		return Month.values()[monthOrdinal];
	}
	/**
	 * Sets this time's month.
	 * @param month new month to set
	 * @return this object
	 */
	public Time setMonth(Month month) {
		calendar.set(Calendar.MONTH, month.getId());
		
		return this;
	}
	
	/** @return day of week */
	public Day getDay() {
		int dayOrdinal = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		
		return Day.values()[dayOrdinal];
	}
	/**
	 * Sets this time's day of the week.
	 * @param day new day of the week to set
	 * @return this object
	 */
	public Time setDay(Day day) {
		calendar.set(Calendar.DAY_OF_WEEK, day.getId());
		
		return this;
	}
	
	/** @return hour */
	public int getHour() {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * Sets this time's hour.
	 * @param hour new hour to set
	 * @return this object
	 */
	public Time setHour(int hour) {
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		
		return this;
	}
	
	/** @return minute */
	public int getMinute() {
		return calendar.get(Calendar.MINUTE);
	}
	/**
	 * Sets this time's minute.
	 * @param minute new minute to set
	 * @return this object
	 */
	public Time setMinute(int minute) {
		calendar.set(Calendar.MINUTE, minute);
		
		return this;
	}
	
	/** @return second */
	public int getSecond() {
		return calendar.get(Calendar.SECOND);
	}
	/**
	 * Sets this time's second.
	 * @param second new second to set
	 * @return this object
	 */
	public Time setSecond(int second) {
		calendar.set(Calendar.SECOND, second);
		
		return this;
	}
	
	@Override
	public int compareTo(Time o) {
		return calendar.compareTo(o.calendar);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calendar == null) ? 0 : calendar.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Time))
			return false;
		Time other = (Time) obj;
		if (calendar == null) {
			if (other.calendar != null)
				return false;
		} else if (!calendar.equals(other.calendar))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		String toString = String.valueOf(getYear()) + TO_STRING_DELIMETER
				+ getMonth() + TO_STRING_DELIMETER
				+ getDay() + TO_STRING_DELIMETER
				+ String.valueOf(getHour())+ TO_STRING_DELIMETER
				+ String.valueOf(getMinute()) + TO_STRING_DELIMETER
				+ String.valueOf(getSecond());
		
		return toString;
	}
	
	/**
	 * Describes a month of the year.
	 */
	@SuppressWarnings("javadoc")
	public static enum Month {
		JANUARY(0),
		FEBRUARY(1),
		MARCH(2),
		APRIL(3),
		MAY(4),
		JUNE(5),
		JULY(6),
		AUGUST(7),
		SEPTEMBER(8),
		OCTOBER(9),
		NOVEMBER(10),
		DECEMBER(11);
		
		private int id;
		
		private Month(int id) {
			this.id = id;
		}
		
		/** @return id representing this month */
		public int getId() {
			return id;
		}
	}
	/**
	 * Describes a day of the week.
	 */
	@SuppressWarnings("javadoc")
	public static enum Day {
		SUNDAY(1),
		MONDAY(2),
		TUESDAY(3),
		WEDNESDAY(4),
		THURSDAY(5),
		FRIDAY(6),
		SATURDAY(7);
		
		private int id;
		
		private Day(int id) {
			this.id = id;
		}
		
		/** @return id representing this day */
		public int getId() {
			return id;
		}
	}
}
