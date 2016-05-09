package dev.se133.project.carpool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dev.se133.project.car.Car;
import dev.se133.project.car.NoDriverException;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.Stop;
import dev.se133.project.member.Member;
import dev.se133.project.member.garage.Vehicle;
import dev.se133.project.parking.ParkingGarage;
import dev.se133.project.parking.ParkingSpot;

/**
 * A one-way trip consisting of a commute and a driver and passengers.
 */
public class Carpool {
	private Commute commute;
	private ParkingSpot parkingSpot;
	private Car car;
	private CarpoolState state = new CarpoolState.Loading();
	private ArrayList<Member> pickedUp = new ArrayList<Member>();
	private List<CarpoolListener> listeners = new LinkedList<>();

	/**
	 * Constructs a new carpool with a set commute and car.
	 * @param commute commute traveled in this carpool
	 * @param car car traveling in this carpool
	 * @throws NoDriverException is the specified car does not have a driver
	 */
	public 
	Carpool
	(Commute commute, Car car) throws NoDriverException {
		setCommute(commute);
		setCar(car);
		parkingSpot = ParkingGarage.requestSpot(this);
	}
	
	public ParkingSpot getParkingSpot()
	{
		return parkingSpot;
	}
	/**
	 * Dispatches this carpool.
	 * @throws IllegalStateException if this method cannot be performed during this carpool's current state
	 */
	public void dispatch() {
		state.dispatch(this);
		for (CarpoolListener listener : listeners)
			listener.dispatched(this);
	}
	private void notifyDispatch() {
		for (CarpoolListener listener : listeners)
			listener.dispatched(this);
	}
	/**
	 * {@link CarpoolState} accessor to private {@link #notifyDispatch()}.
	 */
	void notifyDispatchState() {
		notifyDispatch();
	}
	
	public ArrayList<Member> getPickedUp()
	{
		return pickedUp;
	}
	
	/**
	 * Advances this carpool to the next stop.
	 * @return next stop, or {@code null} if no more stops
	 */
	public Stop nextStop() {
		if (!isAtEnd()) {
			Stop currentStop = commute.nextStop();
			
			for(Member mem : car.getPassengers())
			{
				if(mem.getAddress() == currentStop.getAddress())
				{
					pickedUp.add(mem);
					break;
				}
			}
			notifyHitStop();
				
			if (isAtEnd()) {	// Last stop
				notifyHitEnd();
			}
			
			return currentStop();
		}
		return null;
	}
	public boolean hasNextStop()
	{
		return commute.hasNextStop();
	}
	/**
	 * Returns the last hit stop of this carpool.
	 * @return last hit stop
	 */
	public Stop currentStop() {
		return commute.getCurrent();
	}
	
	/** @return {@code true} if this carpool has reached its destination */
	public boolean isAtEnd() {
		return !commute.hasNextStop();	// If there is a next stop, not at end yet
	}
	
	/** @return	{@code true} if this carpool has a designated driver */
	public boolean hasDriver() {
		return (car != null && car.hasDriver());
	}
	
	/** @return commute traveled in this carpool, or {@code null} if there is none */
	public Commute getCommute() {
		return commute;
	}
	/**
	 * Sets the commute of this carpool.
	 * @param newCommute new commute to set
	 */
	public void setCommute(Commute newCommute) {
		state.setCommute(this, newCommute);
	}
	void stateSetCommute(Commute newCommute) {
		this.commute = newCommute;
	}
	
	/** @return car of this carpool, or {@code null} if there is none */
	public Car getCar() {
		return car;
	}
	/** 
	 * Sets the car of this carpool.
	 * @param newCar new car to set
	 */
	public void setCar(Car newCar) {
		state.setCar(this, newCar);
	}
	
	void stateSetCar(Car newCar) {
		this.car = newCar;
	}
	
	public void setParkingSpot(ParkingSpot spot) {
		state.setParkingSpot(this, spot);
	}
	
	public Vehicle getVehicle()
	{
		return car.getVehicle();
		//return this.car.getDriver().getRegisteredVehicles().getDefaultVehicle();
	}
	
	public void setState(CarpoolState newState)
	{
		this.state = newState;
	}
	/**
	 * Adds a listener to this list of listeners.
	 * @param listener listener to add
	 */
	public void addListener(CarpoolListener listener) {
		listeners.add(listener);
	}
	private void notifyHitStop() {
		for (CarpoolListener listener : listeners)
			listener.hitStop(commute.getCurrent());
	}
	private void notifyHitEnd() {
		for (CarpoolListener listener : listeners)
			listener.hitEnd(commute.getCurrent());
	}
	
	@Override
	public String toString() {
		String toString = "Car: " + car.toString() + System.lineSeparator() +
											"Commute: " + commute.toString();
		
		return toString;
	}
}
