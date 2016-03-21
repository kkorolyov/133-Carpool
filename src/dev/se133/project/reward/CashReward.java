package dev.se133.project.reward;

import dev.se133.project.member.Member;

/**
 * Describes a reward of cash.
 */
public class CashReward implements RewardType {
	private long cashCount;
	
	/**
	 * Constructs a {@code PointReward} with a custom reward count
	 * @param cash amount of cash to reward
	 */
	public CashReward(long cash) {
		this.cashCount = cash;
	}
	
	@Override
	public void reward(Member toReward) {
		toReward.getWallet().getCash().addToCount(cashCount);
	}
}
