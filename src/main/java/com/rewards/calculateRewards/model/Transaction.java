package com.rewards.calculateRewards.model;

import java.time.LocalDate;

/**
 * Represents a customer purchase transaction.
 */
public class Transaction {
	private Long id;
	private Long customerId;
	private double amount;
	private LocalDate transactionDate;

	public Transaction(Long id, Long customerId, double amount, LocalDate transactionDate) {
		this.id = id;
		this.customerId = customerId;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
}