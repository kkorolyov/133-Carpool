package dev.se133.project.entity.vehicle;

/**
 * Describes a vehicle manufacturer.
 */
@SuppressWarnings("javadoc")
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
