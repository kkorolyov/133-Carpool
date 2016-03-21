package dev.se133.project.member.garage;

/**
 * Describes a vehicle.
 */
public class Vehicle {
	private static final int minYear = 1900, maxYear = 2020;	// TODO Specify in properties
	
	private Make make;
	private String model;
	private int year;
	private String vin;
	private int capacity;
	
	/**
	 * Constructs a new vehicle.
	 * @param make vehicle manufacturer
	 * @param model	model name
	 * @param year release year
	 * @param vin vin
	 * @throws YearOutOfBoundsException if the specified year is out of bounds
	 */
	public Vehicle(Make make, String model, int year, String vin, int capacity) throws YearOutOfBoundsException {
		this.make = make;
		this.model = model;
		setYear(year);
		this.vin = vin;
		this.capacity = capacity;
	}
	private void setYear(int year) throws YearOutOfBoundsException {
		if (year < minYear || year > maxYear)
			throw new YearOutOfBoundsException(year, minYear, maxYear);
		
		this.year = year;
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
	/** @return vehicle capacity */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * Describes a vehicle manufacturer.
	 */
	@SuppressWarnings("javadoc")
	public enum Make {
		ABARTH("Abarth"),
		ALFAROMEO("Alfa Romeo"),
		ASTONMARTIN("Aston Martin"),
		AUSTIN("Austin"),
		AUDI("Audi"),
		
		BEDFORD("Bedford"),
		BENTLEY("Bentley"),
		BMW("BMW"),
		BOLWELL("Bolwell"),
		BUFORI("Bufori"),
		
		CADILLAC("Cadillac"),
		CATERHAM("Caterham"),
		CHERY("Chery"),
		CHEVROLET("Chevrolet"),
		CHRYSLER("Chrysler"),
		CITROEN("Citroen"),
		
		DAEWOO("Daewoo"),
		DAIHATSU("Daihatsu"),
		DODGE("Dodge"),
		
		FIAT("Fiat"),
		FORD("Ford"),
		
		GEELY("Geely"),
		GREATWALL("Great Wall"),
		
		HINO("Hino"),
		HONDA("Honda"),
		HUMMER("Hummer"),
		HYUNDAI("Hyundai"),
		
		INFINITI("Infiniti"),
		ISUZU("Isuzu"),
		JAGUAR("Jaguar"),
		JEEP("Jeep"),
		
		KIA("KIA"),
		
		LANDROVER("Land Rover"),
		LEXUS("Lexus"),
		LOTUS("Lotus"),
		
		MAZDA("Mazda"),
		MERCEDESBENZ("Mercedes-Benz"),
		MINI("Mini"),
		MITSUBISHI("Mitsubishi"),
		
		NISSAN("Nissan"),
		
		OPEL("Opel"),
		
		PORSCHE("Porsche"),
		PROTON("Proton"),
		
		RANGEROVER("Range Rover"),
		RENAULT("Renault"),
		
		SAAB("Saab"),
		SKODA("Skoda"),
		SSANGYONG("Ssangyong"),
		SUBARU("Subaru"),
		SUZUKI("Suzuki"),
		
		TAT("Tata"),
		TOYOTA("Toyota"),
		
		VOLKSWAGEN("Volkswagen"),
		VOLVO("Volvo");
		
		private String name;
		
		private Make(String name) {
			this.name= name;
		}
		
		/** @return manufacturer name */
		public String getName() {
			return name;
		}
	}
}
