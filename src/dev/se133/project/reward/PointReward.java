package dev.se133.project.reward;

import dev.se133.project.member.Member;

/**
 * Describes a reward of points.
 */
public class PointReward implements RewardType {
	private long pointCount;
	
	/**
	 * Constructs a {@code PointReward} with a custom reward count
	 * @param points count of points to reward
	 */
	public PointReward(long points) {
		this.pointCount = points;
	}
	@Override
	public void deduct(Member toReward)
	{
		toReward.getWallet().getPoints().removeFromCount(pointCount);
	}
	@Override
	public void reward(Member toReward) {
		toReward.getWallet().getPoints().addToCount(pointCount);
	}
	@Override
	public String toString()
	{
		return ("Point : " + pointCount);
	}
}
