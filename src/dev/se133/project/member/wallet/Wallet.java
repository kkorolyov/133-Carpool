package dev.se133.project.member.wallet;

import java.util.HashSet;
import java.util.Set;

import dev.se133.project.member.wallet.loot.Cash;
import dev.se133.project.member.wallet.loot.Loot;
import dev.se133.project.member.wallet.loot.Points;

/**
 * A wallet holding various loot.
 */
public class Wallet {
	private static final String TO_STRING_LOOT_DELIMETER = ", ";
	
	private Set<Loot> allLoot;
	
	/**
	 * Constructs an empty wallet.
	 */
	public Wallet() {
		allLoot = new HashSet<>();
	}
	
	/** @return number of points in this wallet */
	public long getNumPoints() {
		return getPoints().getCount();
	}
	/** @return amount of cash in this wallet */
	public long getNumCash() {
		return getCash().getCount();
	}
	
	/** @return the points stored in this wallet */
	public Points getPoints() {
		return getLoot(Points.class);
	}
	/** @return the cash stored in this wallet */
	public Cash getCash() {
		return getLoot(Cash.class);
	}
	@SuppressWarnings("unchecked")
	private <T extends Loot> T getLoot(Class<T> lootClass) {
		for (Loot loot : allLoot) {
			if (loot.getClass() == lootClass)
				return (T) loot;
		}
		T newLoot = null;
		try {
			newLoot = lootClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		allLoot.add(newLoot);
		return newLoot;
	}
	
	@Override
	public String toString() {
		StringBuilder toStringBuilder = new StringBuilder("Wallet: ");
		
		int lootCounter = 0;
		for (Loot loot : allLoot) {
			toStringBuilder.append(loot);
			if (++lootCounter < allLoot.size())
				toStringBuilder.append(TO_STRING_LOOT_DELIMETER);
		}
		return toStringBuilder.toString();
	}
}
