package dev.se133.project.reward.rewarder;

import dev.se133.project.carpool.Carpool;
import dev.se133.project.member.Member;
import dev.se133.project.reward.RewardType;

/**
 * Rewards only the driver of a carpool.
 */
public class DriverRewarder extends Rewarder {
	private Carpool carpoolToReward;

	/**
	 * Constructs a rewarder for a specified carpool and reward type.
	 * @param toReward carpool to reward driver of
	 * @param rewardType type of reward to reward with
	 */
	public DriverRewarder(Carpool toReward, RewardType rewardType) {
		super(rewardType);
		this.carpoolToReward = toReward;
	}
	
	@Override
	public void reward() {
		Member driver = carpoolToReward.getCar().getDriver();
		
		getRewardType().reward(driver);
	}
}
