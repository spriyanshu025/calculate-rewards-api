package com.rewards.calculateRewards.service;

import com.rewards.calculateRewards.model.RewardSummary;
import com.rewards.calculateRewards.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RewardServiceTest {

	private RewardService rewardService;

	@BeforeEach
	void setUp() {
		rewardService = new RewardService();
	}

	@Test
	void testCalculatePoints_Under50() {
		assertEquals(0, rewardService.calculatePoints(40.0));
	}

	@Test
	void testCalculatePoints_Between50And100() {
		assertEquals(25, rewardService.calculatePoints(75.0));
	}

	@Test
	void testCalculatePoints_Over100() {
		assertEquals(90, rewardService.calculatePoints(120.0));
	}

	@Test
	void testCalculateRewardsForCustomer_Success() {
		List<Transaction> transactions = Arrays.asList(
				new Transaction(1L, 1L, 120.0, LocalDate.of(2023, Month.JANUARY, 10)),
				new Transaction(2L, 1L, 80.0, LocalDate.of(2023, Month.FEBRUARY, 15)));

		RewardSummary summary = rewardService.calculateRewardsForCustomer(1L, transactions);

		assertEquals(1L, summary.getCustomerId());
		assertEquals(120, summary.getTotalPoints());
		assertEquals(90, summary.getPointsPerMonth().get("JANUARY"));
		assertEquals(30, summary.getPointsPerMonth().get("FEBRUARY"));
	}

	@Test
	void testCalculateRewardsForCustomer_NoTransactionsFound() {
		List<Transaction> transactions = Collections.singletonList(new Transaction(1L, 2L, 100.0, LocalDate.now()));

		Exception exception = assertThrows(RuntimeException.class, () -> {
			rewardService.calculateRewardsForCustomer(1L, transactions);
		});

		assertTrue(exception.getMessage().contains("No transactions found"));
	}
}