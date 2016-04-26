package dev.se133.project.car.parking;

import dev.se133.project.commute.Time;


public class TimeFrame 
{
	Time startTime;
	Time endTime;
	public TimeFrame(Time time, double durInHours)
	{
		startTime = time;
		int durInSeconds = (int) (durInHours*60*60);
		endTime = time.timeAfter(startTime, durInSeconds);
	}
	
	boolean inTime(Time currentTime)
	{
		if(endTime.compareTo(currentTime) == 1 && currentTime.compareTo(startTime) == 1)
		{
			return true;
		}
		return false;
	}
}
