package dev.se133.carpool.member.property.vehicle;

/**
 * Describes a vehicle manufacturer.
 */
public enum Make {
	TOYOTA("Toyota"),
	HONDA("Honda"),
	LEXUS("Lexus"),
	FORD("Ford");
	
	private String name;
	
	private Make(String name) {
		this.name= name;
	}
	
	/** @return manufacturer name */
	public String getName() {
		return name;
	}
}
