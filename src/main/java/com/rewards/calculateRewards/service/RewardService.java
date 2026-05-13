package com.rewards.calculateRewards.service;

import com.rewards.calculateRewards.model.RewardSummary;
import com.rewards.calculateRewards.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class responsible for calculating reward points based on transaction
 * data.
 */
@Service
public class RewardService {

	/**
	 * Calculates the reward points for a given transaction amount.
	 */
	public int calculatePoints(double amount) {
		int dollars = (int) Math.floor(amount);
		if (dollars <= 50) {
			return 0;
		} else if (dollars <= 100) {
			return dollars - 50;
		} else {
			return (2 * (dollars - 100)) + 50;
		}
	}

	/**
	 * Calculates the reward summary for a specific customer based on their
	 * transactions.
	 */
	public RewardSummary calculateRewardsForCustomer(Long customerId, List<Transaction> transactions) {
		if (transactions == null || transactions.isEmpty()) {
			throw new IllegalArgumentException("Transaction list cannot be null or empty");
		}

		List<Transaction> customerTransactions = transactions.stream().filter(t -> t.getCustomerId().equals(customerId))
				.toList();

		if (customerTransactions.isEmpty()) {
			throw new RuntimeException("No transactions found for customer ID: " + customerId);
		}

		// Group by dynamically obtained month and sum points
		Map<String, Integer> monthlyPoints = customerTransactions.stream()
				.collect(Collectors.groupingBy(t -> t.getTransactionDate().getMonth().toString(),
						Collectors.summingInt(t -> calculatePoints(t.getAmount()))));

		int totalPoints = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();

		return new RewardSummary(customerId, monthlyPoints, totalPoints);
	}
}