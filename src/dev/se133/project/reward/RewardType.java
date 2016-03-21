package dev.se133.project.reward;

import dev.se133.project.member.Member;

/**
 * Provides for rewarding members with rewards.
 */
public interface RewardType {
	/**
	 * Rewards a member with this reward.
	 * @param toReward member to reward
	 */
	void reward(Member toReward);
}
