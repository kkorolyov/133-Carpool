package dev.se133.project.reward.rewarder;

import dev.se133.project.car.Car;
import dev.se133.project.carpool.Carpool;
import dev.se133.project.commute.Address;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.Stop;
import dev.se133.project.commute.Time;
import dev.se133.project.commute.Time.Day;
import dev.se133.project.commute.Time.Month;
import dev.se133.project.member.Member;
import dev.se133.project.member.garage.Garage;
import dev.se133.project.member.garage.Vehicle;
import dev.se133.project.member.garage.YearOutOfBoundsException;
import dev.se133.project.member.garage.Vehicle.Make;
import dev.se133.project.member.preferences.CommuteSchedule;
import dev.se133.project.member.wallet.Wallet;
import dev.se133.project.reward.CashReward;
import dev.se133.project.reward.PointReward;
import dev.se133.project.reward.RewardType;

public class RewarderTester {
	private static final long CASH_REWARD_COUNT = 10, POINT_REWARD_COUNT = 50;
	private static final RewardType[] REWARD_TYPES = {new CashReward(CASH_REWARD_COUNT), new PointReward(POINT_REWARD_COUNT)};
	private static final int NUM_STOPS = 10;
	private static final Day DAY = Day.MONDAY;
	private static final int START_TIME = 7 * 60;
	private static final int CAR_CAPACITY = 5;
	private static final int year = 2016;
	private static final Month month = Month.DECEMBER;
	private static final int second = 0;
	
	private static Commute commute;
	private static Car car;
	private static Carpool carpool;

	public static void main(String[] args)
	{
		setup();
	}
	private static void setup()
	{	
		car = new Car(CAR_CAPACITY);
		populateCar();
		
		commute = new Commute();
		populateCommute(car);
		
		carpool = new Carpool(commute, car);
	}
	private static void populateCommute(Car localCar)
	{
		Address currentAddress;
		Time currentTime = new Time();
		
		for(Member mem : localCar.getInhabitants())
		{
			currentAddress = mem.getAddress();
			currentTime = Time.timeAfter(currentTime, (int) (Math.random()*9) + 1);
			Stop currentStop = new Stop(currentTime, currentAddress);
			
			commute.addStop(currentStop);
		}
		//System.out.println(commute.toString());
	}

	private static void populateCar() 
	{
		int id;
		for (int i = 0; i < car.getCapacity(); i++) 
		{
			id =i+1;
			Member a = new Member(id, "Member " + id, i == 0 ? true : false,
					new Address("MemberAddress " + i), new Wallet(), new Garage(), new CommuteSchedule());
			//System.out.println(a.getName());
			if(i == 0)
			{
				car.addDriver(a);
				
				try 
				{
					a.getRegisteredVehicles().addVehicle("Car " + id, new Vehicle(Make.ABARTH, "red", 2015, "v" + i , 5));
				} catch (YearOutOfBoundsException e) 
				{
					e.printStackTrace();
				}
			}
			else
				car.addPassenger(a);
			
			//System.out.println(i);
		}
	}
}
