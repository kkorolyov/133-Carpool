package dev.se133.project.reward.rewarder;

import dev.se133.project.carpool.Carpool;
import dev.se133.project.member.Member;
import dev.se133.project.reward.RewardType;

public class RewardMachine extends Rewarder
{
	private Member memberToReward;
	
	/**
	 * Constructs a rewarder for a specified carpool and reward type.
	 * @param toReward carpool to reward
	 * @param rewardType type of reward to reward with
	 */
	public RewardMachine(RewardType rewardType) {
		super(rewardType);
	}
	/**
	 * Rewards @param memberToReward with @param rewardType
	 */
	public void reward(Member memberToReward, RewardType rewardType)
	{
		this.rewardType = rewardType;
		this.memberToReward = memberToReward;
		reward();
	}
	public void deduct(Member memberToReward, RewardType rewardType)
	{
		if(memberToReward != null)
			getRewardType().deduct(memberToReward);
	}
	@Override
	public void reward() 
	{
		if(memberToReward != null)
			getRewardType().reward(memberToReward);
	}
	
}
