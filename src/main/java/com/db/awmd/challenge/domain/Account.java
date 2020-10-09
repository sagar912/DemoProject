package com.db.awmd.challenge.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

import javax.validation.constraints.Min;

import javax.validation.constraints.NotNull;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

@Data

public class Account {

	@NotNull
	@NotEmpty
	private String accountId;

	@NotNull
	@Min(value = 0, message = "Initial balance must be positive.")

	private BigDecimal balance;
	
	@JsonProperty(required = true)
	private BigDecimal amount;

	@JsonProperty(required = true)
	private Long fromAccountId;

	@JsonProperty(required = true)
	private Long toAccountId;

	

	public Account(String accountId, BigDecimal amount, Long fromAccountId, Long toAccountId) {
		this.accountId = "";
		this.amount = amount;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.balance = BigDecimal.ZERO;

	}


	@JsonCreator
	public Account(@JsonProperty("accountId") String accountId, @JsonProperty("balance") BigDecimal balance) {		this.accountId = "";
	this.accountId = accountId;
		this.balance = balance;

	}

}
