package dev.se133.project.entity;

import dev.se133.project.entity.exception.FullCarException;
import dev.se133.project.entity.member.Member;

/**
 * Representation of the inhabitants of a car.
 * Contains exactly a finite number of inhabitants, 1 of which is the designated driver.
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
	// TODO Almost everything
}
