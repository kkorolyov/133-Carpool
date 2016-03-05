package dev.se133.project.commute;

import dev.se133.project.member.Member;

/**
 * Representation of the inhabitants of a car.
 * Contains a finite number of inhabitants, exactly 1 of which is the designated driver.
 */
public class Car {
	/** Default maximum number of inhabitants */
	public static final int DEFAULT_SIZE = 5;
	
	private Member driver;	// Reference to one of the inhabitants
	private Member[] inhabitants;
	private boolean isFull;
	
	/**
	 * Constructs a car of default size.
	 */
	public Car() {
		this(DEFAULT_SIZE);
	}
	/**
	 * Constructs a car of a specified max size.
	 * @param size maximum number of inhabitants
	 */
	public Car(int size) {
		inhabitants = new Member[size];
		isFull = false;
	}
	
	/**
	 * adds a new inhabitant to the car
	 * throws exception if the car is full
	 * @param inhabitant
	 * @throws FullCarException
	 */
	public void addPassenger(Member inhabitant) throws FullCarException {
		int i, len = inhabitants.length;
		if(!isFull) {
			for(i = 0; i < len; i++) {
				if(inhabitants[i] == null) {
					inhabitants[i] = inhabitant;
				}
				if(i == len-1) {
					isFull = true;
				}
			}
		}
		else throw new FullCarException(len);
	}
	
	/**
	 * finds specified member within inhabitants array
	 * sets the element to null
	 * sets isFull to false
	 * @param inhabitant
	 */
	public void removePassenger(Member inhabitant) {
		int i, len = inhabitants.length;
		for(i = 0; i < len; i++) {
			if(inhabitants[i].equals(inhabitant)) {
				inhabitants[i] = null;
				isFull = false;
				return;
			}
		}
	}
	// TODO Almost everything
}
