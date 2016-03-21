package dev.se133.project.member.wallet.loot;

/**
 * A dynamic count of "points".
 */
public class Points extends Loot {
	private static final String LOOT_NAME = "Points";
	
	/**
	 * Constructs a {@code Points} object with 0 count.
	 */
	public Points() {
		super();
	}
	/**
	 * Constructs a {@code Points} object with a custom initial count.
	 * @param initialCount initial number of points
	 */
	public Points(long initialCount) {
		super(initialCount);
	}
	
	@Override
	public String getLootName() {
		return LOOT_NAME;
	}
}
