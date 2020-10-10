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
	
	private Long amount;

	private String fromAccountId;

	private String toAccountId;

	

	public Account(String accountId, Long amount, String fromAccountId, String toAccountId) {
		this.accountId = accountId;
		this.amount = amount;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.balance = BigDecimal.ZERO;

	}


	@JsonCreator
	public Account(@JsonProperty("accountId") String accountId, @JsonProperty("balance") BigDecimal balance,@JsonProperty("amount")Long amount,@JsonProperty("fromAccountId") String fromAccountId, @JsonProperty("toAccountId") String  toAccountIdLong) {		
    	  
		this.accountId = accountId;
		this.balance = balance;
		this.amount = amount;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
	}

}
