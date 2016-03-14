package dev.se133.project.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Centralized manager for all members.
 */
public class MemberManager {
	private int numBuckets = 10, bucketSize = 20;
	private static MemberManager instance;
	
	private Map<Integer, Member> members = new HashMap<>();
	
	/**
	 * bucket list of drivers sorted by distances from SJSU 
	 * where the first bucket will be a range of distances 
	 * starting at zero
	 * EX: first bucket can be 0-5 miles out
	 */
	private Member[][] driverBuckets = new Member[numBuckets][bucketSize];
	private Member[][] passengerBuckets = new Member[numBuckets][bucketSize];
	
	/**
	 * Returns the sole manager for the current runtime.
	 * @return manager
	 */
	public static MemberManager getManager() {
		if (instance == null)
			instance = new MemberManager();
		return instance;
	}
	private MemberManager() {
		// Private constructor
	}
	
	// TODO Management methods
	public Member[] getSameBucketPassengers(Member driver) {
		for(int i = 0; i < numBuckets; i++) {
			for(int j = 0; j < bucketSize; i++) {
				if(driver.equals(driverBuckets[i][j])) {
					return passengerBuckets[i];
				}
			}
		}
		return passengerBuckets[0];
	}
}
