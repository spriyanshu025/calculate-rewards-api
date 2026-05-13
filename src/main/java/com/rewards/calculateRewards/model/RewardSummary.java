package com.rewards.calculateRewards.model;

import java.util.Map;

/**
 * Encapsulates the reward points summary for a specific customer.
 */
public class RewardSummary {
	private Long customerId;
	private Map<String, Integer> pointsPerMonth;
	private int totalPoints;

	public RewardSummary(Long customerId, Map<String, Integer> pointsPerMonth, int totalPoints) {
		this.customerId = customerId;
		this.pointsPerMonth = pointsPerMonth;
		this.totalPoints = totalPoints;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Map<String, Integer> getPointsPerMonth() {
		return pointsPerMonth;
	}

	public void setPointsPerMonth(Map<String, Integer> pointsPerMonth) {
		this.pointsPerMonth = pointsPerMonth;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
}