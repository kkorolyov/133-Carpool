package dev.se133.project.member.wallet.loot;

/**
 * A dynamic count of an abstract reward.
 */
public abstract class Loot {
	private static final char TO_STRING_DELIMETER = ' ';
	
	private long count;
	
	/**
	 * Constructs a loot with 0 count.
	 */
	public Loot() {
		count = 0;
	}
	/**
	 * Constructs a loot with a specified initial count.
	 * @param initialCount initial count of this loot
	 */
	public Loot(long initialCount) {
		count = initialCount;
	}
	
	/** @return	count of this loot */
	public long getCount() {
		return count;
	}
	/**
	 * Adds to this loot's count.
	 * Returns the result after addition.
	 * @param amountToAdd amount to add to count, if negative, will remove this amount from count
	 * @return loot count after adding
	 */
	public long addToCount(long amountToAdd) {
		return count += amountToAdd;
	}
	/**
	 * Removes from this loot's count.
	 * Returns the result after removal.
	 * The same result can be achieved by specifying a negative amount in {@link #addToCount(long)}.
	 * @param amountToRemove amount to remove from count
	 * @return loot count after removing
	 */
	public long removeFromCount(long amountToRemove) {
		return addToCount(-amountToRemove);
	}
	
	/** @reward the identifying name of this loot type */
	public abstract String getLootName();
	
	@Override
	public String toString() {
		String toString = String.valueOf(count) + TO_STRING_DELIMETER + getLootName();
		
		return toString;
	}
}
