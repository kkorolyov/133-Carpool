package dev.se133.project.carpool;

import java.util.LinkedList;
import java.util.List;

import dev.se133.project.car.Car;
import dev.se133.project.car.NoDriverException;
import dev.se133.project.commute.Commute;
import dev.se133.project.commute.Stop;

/**
 * A one-way trip consisting of a commute and a driver and passengers.
 */
public class Carpool {
	private Commute commute;
	private Car car;
	private CarpoolState state = new CarpoolState.Loading();
	private List<CarpoolListener> listeners = new LinkedList<>();

	/**
	 * Constructs a new carpool with a set commute and car.
	 * @param commute commute traveled in this carpool
	 * @param car car traveling in this carpool
	 * @throws NoDriverException is the specified car does not have a driver
	 */
	public Carpool(Commute commute, Car car) throws NoDriverException {
		setCommute(commute);
		setCar(car);
	}
	
	/** @return commute traveled in this carpool, or {@code null} if there is none */
	public Commute getCommute() {
		return commute;
	}
	/**
	 * Sets the commute of this carpool.
	 * @param commute new commute
	 */
	public void setCommute(Commute commute) {
		this.commute = commute;
	}
	
	/**
	 * Advances this carpool to the next stop.
	 * @return next stop, or {@code null} if no more stops
	 */
	public Stop nextStop() {
		if (!isAtEnd()) {
			commute.nextStop();
		
			notifyHitStop();
				
			if (isAtEnd())	// Last stop
				notifyHitEnd();
			
			return currentStop();
		}
		return null;
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
	
	/** @return car of this carpool, or {@code null} if there is none */
	public Car getCar() {
		return car;
	}
	/** 
	 * Sets the car of this carpool.
	 * @param car new car
	 * @throws NoDriverException if the specified car does not have a driver
	 */
	public void setCar(Car car) throws NoDriverException {
		if (car.getDriver() == null)
			throw new NoDriverException();	// No carpool if no driver
		
		this.car = car;
	}
	
	void setState(CarpoolState newState)
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
}
