package dev.se133.project.parking;


import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.Time;
import dev.se133.project.member.*;

public class ParkingSpot 
{
	private Carpool occupiedBy;
	private int parkingSpotNumber;
	private TimeFrame timeFrame;
	
	/**
	 * Constructor
	 */
	ParkingSpot(int psn)
	{
		occupiedBy = null;	
		parkingSpotNumber = psn;
		
	}
	public int getParkingSpotNumber()
	{
		return parkingSpotNumber;
	}
	
	/**
	 * Fills the parking spot with @param c
	 * @return true if spot is filled
	 * @return false if spot is taken
	 */
	public boolean fill(Carpool car)
	{
		if(vacant())
		{
			occupiedBy = car;			
			return true;
		}
		return false;
	}
	
	/*
	 * Need to implement notification for when 
	 * vehicle is parked outside of time frame
	 */
	public void setTime(Carpool carpool)
	{
		Time startTime = carpool.getCommute().getEnd().getTime();
		Member driver = carpool.getCar().getDriver();
		Time endTime = calculateEndTime(startTime);	// Stub
		timeFrame = new TimeFrame(startTime, endTime);
	}
	private static Time calculateEndTime(Time startTime) {
		return Time.timeAfter(startTime, 60 * 60);
	}

	/**
	 * removes vehicle from parking spot;
	 */
	public void remove()
	{
		occupiedBy = null;
		timeFrame = null;
	}
	
	/**
	 * @return true if parking spot is empty
	 * @return false if parking spot is occupied
	 */
	private boolean vacant() 
	{
		return occupiedBy == null;
	}
	
	@Override
	public String toString()
	{
		if(vacant())
			return "_________";
		return occupiedBy.getVehicle().toString();
	}
	/**
	 * @return true if parking spot is occupied
	 */
	public boolean isOccupied() {
		return occupiedBy != null;
	}
}
