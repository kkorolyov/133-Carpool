package dev.se133.project.member.garage;

public class ParkingSpot 
{
	private Vehicle occupiedBy;
	private int parkingSpotNumber;
	
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
	public boolean fill(Vehicle carVehicle)
	{
		if(vacant())
		{
			occupiedBy = carVehicle;
			return true;
		}
		return false;
	}

	/**
	 * removes vehicle from parking spot;
	 */
	public void remove()
	{
		occupiedBy = null;
	}
	
	/**
	 * @return true if parking spot is empty
	 * @return false if parking spot is occupied
	 */
	private boolean vacant() 
	{
		return occupiedBy == null;
	}
}
