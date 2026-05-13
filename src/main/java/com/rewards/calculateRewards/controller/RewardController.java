package com.rewards.calculateRewards.controller;

import com.rewards.calculateRewards.model.RewardSummary;
import com.rewards.calculateRewards.model.Transaction;
import com.rewards.calculateRewards.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * REST Controller providing endpoints for the Rewards Program.
 */
@RestController
@RequestMapping("/api/rewards")
public class RewardController {

	private final RewardService rewardService;

	@Autowired
	public RewardController(RewardService rewardService) {
		this.rewardService = rewardService;
	}

	// Mock data set
	private final List<Transaction> mockTransactions = Arrays.asList(
			new Transaction(1L, 101L, 120.0, LocalDate.now().minusMonths(2)), // 90 points
			new Transaction(2L, 101L, 75.0, LocalDate.now().minusMonths(1)), // 25 points
			new Transaction(3L, 101L, 200.0, LocalDate.now()), // 250 points
			new Transaction(4L, 102L, 40.0, LocalDate.now().minusMonths(1)) // 0 points
	);

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<RewardSummary> getRewardsByCustomer(@PathVariable Long customerId) {
		RewardSummary summary = rewardService.calculateRewardsForCustomer(customerId, mockTransactions);
		return new ResponseEntity<>(summary, HttpStatus.OK);
	}
}