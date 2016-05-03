package dev.se133.project.car.parking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.Address;
import dev.se133.project.member.garage.Vehicle;

public class ParkingGarage {
	private static final int NUM_OF_SPOTS = 10;
	private static Address address;
	private static ParkingSpot[] parkingSpots;
	private static ArrayList<ParkingSpot> availableSpots;
	
	private static HashMap<ParkingSpot, Carpool> assignedSpots;
	private static HashMap<ParkingSpot, Carpool> filledSpots;
	
	public ParkingGarage()
	{
		address = new Address(" Washington Sq, San Jose, CA 95192");
		availableSpots = new ArrayList<ParkingSpot>();
		assignedSpots = new HashMap<ParkingSpot, Carpool>();
		filledSpots = new HashMap<ParkingSpot, Carpool>();
		parkingSpots = new ParkingSpot[NUM_OF_SPOTS];
		for(int i = 0; i < NUM_OF_SPOTS; i++)
		{
			parkingSpots[i] = new ParkingSpot(i);
			availableSpots.add(parkingSpots[i]);
		}
	}

	/**
	 * 
	 * @param Carpool car to add
	 * @return -1 if parking garage is full
	 * @return 0 if successful
	 * @return 1 for unsuccessful add
	 */
	private static boolean add(Carpool car)
	{
		if(availableSpots.isEmpty())
			return false;
		if(parkingSpots[availableSpots.get(0).getParkingSpotNumber()].fill(car))
		{
			System.out.println("PARKING GARAGE - ADD(VEHICLE) - PARKINGSPOT - FILL - " + availableSpots.get(0).getParkingSpotNumber());
			for(int i = 0; i < availableSpots.size(); i++)
			{
				System.out.print(availableSpots.get(i).getParkingSpotNumber() + " ");
			}
			System.out.println();
			assignedSpots.put(availableSpots.get(0), car);
			availableSpots.remove(0);
			return true;
		}
		return false;
	}
	
	/**
	 * Vehicle occupying @param parkingSpotNumber left the garage.
	 */
	private static void remove(int parkingSpotNumber)
	{
		parkingSpots[parkingSpotNumber].remove();
		if(!availableSpots.contains(parkingSpots[parkingSpotNumber]))
		{
			availableSpots.add(parkingSpots[parkingSpotNumber]);
		}
		Collections.sort(availableSpots, new Comparator<ParkingSpot>()
				{
					@Override
					public int compare(ParkingSpot one, ParkingSpot two)
					{
						return one.getParkingSpotNumber() - (two.getParkingSpotNumber());
					}
				});
		if(!parkingSpots[parkingSpotNumber].isOccupied())
			return true;
		return false;
	}
	
	private static boolean removeAt(int parkingSpotNumber)
	{
		remove(parkingSpotNumber);
	}
	
	/**
	 * @return true if full
	 */
	private static boolean isFull() {
		return availableSpots.isEmpty();
	}
	
	/**
	 * @return address of parking garage
	 */
	public Address getAddress()
	{
		return address;
	}
	
	public static boolean requestSpot(Carpool car)
	{
		return add(car);
	}
	
	@Override
	public String toString()
	{
		String parkingLot = "| ";
		for(int i = 0; i < parkingSpots.length; i++)
		{
			if(i == 4 || i == 9)
				parkingLot += (parkingSpots[i].toString() + " |" + "\n| ");
			else
				parkingLot += (parkingSpots[i].toString() + " | ");
		}
		return parkingLot;
		
	}
}
