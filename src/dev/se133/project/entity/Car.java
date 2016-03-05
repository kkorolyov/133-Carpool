package dev.se133.project.entity;

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
	}
	
	// TODO Almost everything
}
