package dev.se133.project.car.parking;

import dev.se133.project.commute.Time;


public class TimeFrame 
{
	Time startTime;
	Time endTime;
	public TimeFrame(Time startTime, Time endTime)
	{
		this.startTime = startTime;
		this.endTime = endTime;
		int durInSeconds = Time.getDifference(startTime, endTime);
		endTime = Time.timeAfter(startTime, durInSeconds);
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
