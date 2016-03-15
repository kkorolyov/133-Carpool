package dev.se133.project.commute;

/**
 * A one-way trip consisting of a commute and a driver and passengers.
 */
public class Carpool {
	private Commute commute;
	private Car car;
	
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
}
