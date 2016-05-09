package dev.se133.project.parking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.Address;
import dev.se133.project.member.garage.Vehicle;

public class ParkingGarage {
	private static final int NUM_OF_SPOTS = 10;
	private static Address address = new Address(" Washington Sq, San Jose, CA 95192");
	private static ParkingSpot[] parkingSpots = new ParkingSpot[NUM_OF_SPOTS];
	private static ArrayList<ParkingSpot> availableSpots = new ArrayList<ParkingSpot>();
	
	private static HashMap<ParkingSpot, Carpool> assignedSpots = new HashMap<ParkingSpot, Carpool>();
	private static HashMap<ParkingSpot, Carpool> filledSpots = new HashMap<ParkingSpot, Carpool>();
	
	private static int setup = 0;
	
	public ParkingGarage()
	{
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
	 * calls add upon request
	 * @param car
	 * @return
	 */
	public static ParkingSpot requestSpot(Carpool car)
	{
		return add(car);
	}

	/**
	 * 
	 * @param Carpool car to add
	 * @return null if parking garage is full
	 * @return ParkingSpot if successful
	 * @return null for unsuccessful add
	 */
	private static ParkingSpot add(Carpool car)
	{
		if(availableSpots.isEmpty())
			return null;
		if(parkingSpots[availableSpots.get(0).getParkingSpotNumber()].fill(car))
		{
			ParkingSpot toReturn;
			System.out.println("PARKING GARAGE - ADD(VEHICLE) - PARKINGSPOT - FILL - " + availableSpots.get(0).getParkingSpotNumber());
			for(int i = 0; i < availableSpots.size(); i++)
			{
				System.out.print(availableSpots.get(i).getParkingSpotNumber() + " ");
			}
			System.out.println();
			assignedSpots.put(availableSpots.get(0), car);
			availableSpots.get(0).setTime(car);
			if((toReturn = availableSpots.remove(0)) != null) {
				toReturn.setTime(car);
			}
			
			return toReturn;
		}
		return null;
	}
	
	/**
	 * Vehicle occupying @param parkingSpotNumber left the garage.
	 */
	private static boolean remove(int parkingSpotNumber)
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
		return remove(parkingSpotNumber);
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
	public static Address getAddress()
	{
		if(setup == 0)
		{
			for(int i = 0; i < NUM_OF_SPOTS; i++)
			{
				parkingSpots[i] = new ParkingSpot(i);
				availableSpots.add(parkingSpots[i]);
			}
			setup++;
		}
		return address;//new Address(" Washington Sq, San Jose, CA 95192");
	}
	
	public static boolean park(int parkingSpotNumber, Carpool car)
	{
		if(filledSpots.get(parkingSpots[parkingSpotNumber]) == null)
		{
			filledSpots.put(parkingSpots[parkingSpotNumber],car);
			System.out.println("Parked in : " + filledSpots.get(car));
			return true;
		}
		return false;
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
