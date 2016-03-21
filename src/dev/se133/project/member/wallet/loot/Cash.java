package dev.se133.project.member.wallet.loot;

/**
 * A dynamic count of "cash".
 */
public class Cash extends Loot {
	private static final String LOOT_NAME = "Cash";
	
	/**
	 * Constructs a {@code Cash} object with 0 count.
	 */
	public Cash() {
		super();
	}
	/**
	 * Constructs a {@code Cash} object with a custom initial count.
	 * @param initialCount initial amount of cash
	 */
	public Cash(long initialCount) {
		super(initialCount);
	}
	
	@Override
	public String getLootName() {
		return LOOT_NAME;
	}
}
