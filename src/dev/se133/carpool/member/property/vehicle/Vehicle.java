package dev.se133.carpool.member.property.vehicle;

/**
 * Describes a vehicle.
 */
public class Vehicle {
	private static final int minYear = 1900, maxYear = 2020;	// TODO Specify in properties
	
	private Make make;
	private String model;
	private int year;
	private String vin;
	
	/**
	 * Constructs a new vehicle.
	 * @param make vehicle manufacturer
	 * @param model	model name
	 * @param year release year
	 * @param vin vin
	 * @throws YearOutOfBoundsException if the specified year is out of bounds
	 */
	public Vehicle(Make make, String model, int year, String vin) throws YearOutOfBoundsException {
		this.make = make;
		this.model = model;
		setYear(year);	// For validation
		this.vin = vin;
	}
	private static void setYear(int year) throws YearOutOfBoundsException {
		if (year < minYear || year > maxYear)
			throw new YearOutOfBoundsException(year, minYear, maxYear);
	}
	
	/** @return vehicle make */
	public Make getMake() {
		return make;
	}
	/** @return vehicle model */
	public String getModel() {
		return model;
	}
	/** @return vehicle release year */
	public int getYear() {
		return year;
	}
	/** @return vehicle vin */
	public String getVin() {
		return vin;
	}
}
