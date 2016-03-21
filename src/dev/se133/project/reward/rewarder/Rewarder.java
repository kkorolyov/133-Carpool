package dev.se133.project.reward.rewarder;

import dev.se133.project.reward.RewardType;

/**
 * The base class for rewarding mechanisms.
 */
public abstract class Rewarder {
	private RewardType rewardType;
	
	/**
	 * Constructs a rewarder for a specified reward type.
	 * @param rewardType type of reward to reward
	 */
	public Rewarder(RewardType rewardType) {
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
