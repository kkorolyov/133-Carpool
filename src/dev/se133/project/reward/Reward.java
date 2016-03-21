package dev.se133.project.reward;

/**
 * The base class for rewarding mechanisms.
 */
public abstract class Reward {
	private RewardType rewardType;
	
	/**
	 * Constructs a reward for a specified reward type.
	 * @param rewardType type of reward to reward
	 */
	public Reward(RewardType rewardType) {
		this.rewardType = rewardType;
	}
	
	/** @return reward type used by this reward */
	public RewardType getRewardType() {
		return rewardType;
	}
	
	/**
	 * Disperses rewards based on the implementation defined by an implementor.
	 */
	public abstract void reward();
}
