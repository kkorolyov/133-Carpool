package dev.se133.project.commute;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A one-way trip consisting of a commute and a driver and passengers.
 */
public class Carpool {
	private Commute commute;
	private Iterator<CommutePoint> commuteIterator;
	private Car car;
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
		commuteIterator = this.commute.getStops().iterator();
	}
	
	/**
	 * Advances this carpool to the next stop.
	 * @return next stop, or {@code null} if no more stops
	 */
	public CommutePoint nextStop() {
		CommutePoint nextStop = commuteIterator.hasNext() ? commuteIterator.next() : null;
		
		if (nextStop != null) {
			notifyHitStop(nextStop);
			
			if (!commuteIterator.hasNext())	// Last stop
				notifyHitEnd();
		}
		return nextStop;
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
	
	/**
	 * Adds a listener to this list of listeners.
	 * @param listener listener to add
	 */
	public void addListener(CarpoolListener listener) {
		listeners.add(listener);
	}
	private void notifyHitStop(CommutePoint stop) {
		for (CarpoolListener listener : listeners)
			listener.hitStop(stop);
	}
	private void notifyHitEnd() {
		for (CarpoolListener listener : listeners)
			listener.hitEnd();
	}
}
