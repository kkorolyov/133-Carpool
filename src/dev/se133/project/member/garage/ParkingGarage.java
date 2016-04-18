package dev.se133.project.member.garage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ParkingGarage {
	private final int NUM_OF_SPOTS = 100;
	private ParkingSpot[] parkingSpots;
	private ArrayList<ParkingSpot> availableSpots;
	
	ParkingGarage()
	{
		availableSpots = new ArrayList<ParkingSpot>();
		parkingSpots = new ParkingSpot[NUM_OF_SPOTS];
		for(int i = 0; i < NUM_OF_SPOTS; i++)
		{
			parkingSpots[i] = new ParkingSpot(i);
			availableSpots.add(parkingSpots[i]);
		}
	}
	/**
	 * 
	 * @param v vehicle to add
	 * @return -1 if parking garage is full
	 * @return 0 if successful
	 * @return 1 for unsuccessful add
	 */
	public int add(Vehicle v)
	{
		if(availableSpots.isEmpty())
			return -1;
		if(parkingSpots[availableSpots.get(0).getParkingSpotNumber()].fill(v))
		{
			availableSpots.remove(0);
			return 0;
		}
		return 1;
	}
	
	/**
	 * Vehicle occupying @param parkingSpotNumber left the garage.
	 */
	public void remove(int parkingSpotNumber)
	{
		parkingSpots[parkingSpotNumber].remove();
		availableSpots.add(parkingSpots[parkingSpotNumber]);
		Collections.sort(availableSpots, new Comparator<ParkingSpot>()
				{
					@Override
					public int compare(ParkingSpot one, ParkingSpot two)
					{
						return one.getParkingSpotNumber() - (two.getParkingSpotNumber());
					}
				});
	}
}
