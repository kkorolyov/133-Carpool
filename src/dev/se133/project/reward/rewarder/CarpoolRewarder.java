package dev.se133.project.reward.rewarder;

import dev.se133.project.carpool.Carpool;
import dev.se133.project.member.Member;
import dev.se133.project.reward.RewardType;

/**
 * Rewards all members of a carpool.
 */
public class CarpoolRewarder extends Rewarder {
	private Carpool carpoolToReward;
	
	/**
	 * Constructs a rewarder for a specified carpool and reward type.
	 * @param toReward carpool to reward
	 * @param rewardType type of reward to reward with
	 */
	public CarpoolRewarder(Carpool toReward, RewardType rewardType) {
		super(rewardType);
		this.carpoolToReward = toReward;
	}
	
	@Override
	public void reward() {
		for (Member member : carpoolToReward.getCar().getInhabitants()) {
			getRewardType().reward(member);
		}
	}
}
