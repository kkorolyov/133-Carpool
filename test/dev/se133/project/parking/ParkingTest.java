package dev.se133.project.parking;

import java.util.ArrayList;

import dev.se133.project.car.Car;
import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.Address;
import dev.se133.project.member.Member;
import dev.se133.project.member.garage.Garage;
import dev.se133.project.member.garage.ParkingGarage;
import dev.se133.project.member.garage.Vehicle;
import dev.se133.project.member.garage.Vehicle.Make;
import dev.se133.project.member.garage.YearOutOfBoundsException;
import dev.se133.project.member.preferences.CommuteSchedule;
import dev.se133.project.member.wallet.Wallet;

public class ParkingTest {
	
	static ArrayList<Carpool> carpools;
	static ParkingGarage parkingLot;
	
	public static void main(String[] args) 
	{
		carpools = new ArrayList<Carpool>();
		parkingLot = new ParkingGarage();
		populateCar(150);
		for(Carpool c : carpools)
		{
			
			if(parkingLot.isFull())
				removeFromGarage((int) (Math.random()*10));
			int i = parkingLot.add(c.getVehicle());
			
			
			if(i == 0)
				System.out.println(c.getVehicle().toString() + ": succuess.");
			else if(i == -1)
				System.out.println(c.getVehicle().toString() + ": PARKING LOT IS FULL");
			else
			
				System.out.println(c.getVehicle().toString() + " : ERROR ADDING");
			
			System.out.println(parkingLot.toString());
		}
	}
	
	
	private static void removeFromGarage(int d) 
	{
		if(d%2 == 0)
		{
			for(int i = 0; i < d; i++)
			{
				parkingLot.remove((int) (Math.random() * 9));
			}
		}
		
	}


	private static void populateCar(int numOfCarpool){
		int id;
		for(int j = 0; j < numOfCarpool; j++)
		{
			Car car = new Car();

			for (int i = 0; i < car.getCapacity(); i++) 
			{
				if(j == 0)
					id = i;
				else
					id = j*10 + i;
				
				Member a = new Member(id, "Member" + id, i == 0 ? true : false,
						new Address("MemberAddress" + i), new Wallet(), new Garage(), new CommuteSchedule());
				if( i == 0)
				{
					car.addDriver(a);
					try 
					{
						a.getRegisteredVehicles().addVehicle("Car " + id, new Vehicle(Make.ABARTH, "red", 2000, "v" + j + i , 5));
					} catch (YearOutOfBoundsException e) 
					{
						e.printStackTrace();
					}
					
				}
				else
					car.addPassenger(a);
				
			}
			carpools.add(new Carpool(car));
		}
	}
}
